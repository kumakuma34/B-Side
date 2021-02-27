package bside.writing.Service;

import bside.writing.Member.Member;
import bside.writing.Repository.MemberRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;
    @Value("${JWT_SECRET}")
    private String JWT_SECRET;
    private final static int tokenLiveTime = 1000 * 2 * 60;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public TokenService(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    public String getAccessToken(String idTokenString) throws GeneralSecurityException, IOException {

        Optional<GoogleIdToken> idToken = Optional.ofNullable(getVerify(idTokenString));
        if(idToken.isEmpty()) return "Invalid id_token";

        GoogleIdToken.Payload payload = idToken.get().getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        //TODO 아래 정보들을 활용할 것인지
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

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
        /*Optional<List<Member>> member = memberRepository.findByUserEmail(email);
        if(member.isEmpty()){
            memberService.join(new Member(email, name,"ROLE_USER", pictureUrl));
        }
        else{
            List<Member> members = member.get();
            Member curMember = members.get(0);
            curMember.setEmail_address(email);
            curMember.setNick_name(name);
            curMember.setPictureURL(pictureUrl);
            //memberService.updateMember(Member member) 구현 필요
        }
        */
    }

    public String makeAccessToken(String email){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setId(email)
                .signWith(signingKey,signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .compact();
    }
}
