package bside.writing.Service;

import bside.writing.domain.member.Member;
import bside.writing.Repository.MemberRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;
    @Value("${JWT_SECRET}")
    private String JWT_SECRET;
    @Value("${ACCESS_TOKEN_LIFETIME}")
    private String ACCESS_TOKEN_LIFETIME;

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public String getAccessToken(String idTokenString) throws GeneralSecurityException, IOException {

        Optional<GoogleIdToken> idToken = Optional.ofNullable(getVerify(idTokenString));
        if(idToken.isEmpty()) return "Invalid id_token";

        GoogleIdToken.Payload payload = idToken.get().getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");

        saveOrUpdateBy(email, name, pictureUrl);
        return makeAccessToken(email);
    }

    public GoogleIdToken getVerify(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        return verifier.verify(idTokenString);
    }

    public void saveOrUpdateBy(String email, String name, String pictureUrl){
        Optional<Member> member = memberRepository.findByUserEmail(email);
        if(!member.isPresent()){
            //memberService.join(new Member(email, name,"ROLE_USER", pictureUrl));
        }
        else{
            //List<Member> members = member.get();
            Member curMember = member.get();

            //curMember.setEmail_address(email);
           // curMember.setNick_name(name);
            //curMember.setProfile_url(pictureUrl);
            //memberService.update(Member member) 구현 필요
        }
    }

    public String makeAccessToken(String email){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setId(email)
                .signWith(signingKey,signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFETIME))
                .compact();
    }

    public boolean checkAccessToken(String accessTokenString){
        return true;
    }

    public boolean checkRefreshToken(String accessTokenString){
        /**
         * if(memberService.getRefreshToken())
         *  email = refreshToken.getEmail();
         *  makeAccessToken(email);
         */
        return true;
    }
    /**
     * 1. access token 확인 유효한지 시간이 지났는지, 유효하다면 관련 정보 send
     * 2. 유효 하지 않다면,
     */
}
