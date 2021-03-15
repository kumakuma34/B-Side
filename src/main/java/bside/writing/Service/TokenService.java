package bside.writing.Service;


import bside.writing.domain.member.MemberTokenRespository;
import bside.writing.dto.MemberDto;
import bside.writing.templateClass.ResponseMessage;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
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
    private int ACCESS_TOKEN_LIFETIME;

    private final MemberTokenRespository memberTokenRespository;

    public MemberDto getUserInfo(String idTokenString) throws Exception {

        GoogleIdToken idToken = Optional.ofNullable(getVerify(idTokenString))
                .orElseThrow(() -> new AuthenticationException(ResponseMessage.UNAUTHORIZED_TOKEN.getMsg()));

        GoogleIdToken.Payload payload = idToken.getPayload();

        String email = payload.getEmail();
        String nickName = (String) payload.get("name");
        String profileUrl = (String) payload.get("picture");

        MemberDto memberDto = MemberDto.builder()
                .email(email)
                .nickName(nickName)
                .profileUrl(profileUrl)
                .build();

        return memberDto;
    }

    public GoogleIdToken getVerify(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        return verifier.verify(idTokenString);
    }

    public byte[] generateKeyAsByte(){
        return DatatypeConverter.parseBase64Binary(JWT_SECRET);
    }

    public String makeAccessToken(Long member_Id){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        final Key signingKey = new SecretKeySpec(generateKeyAsByte(), signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setId(String.valueOf(member_Id))
                .signWith(signingKey,signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFETIME))
                .compact();
    }

    public Long getUID(String accessToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(generateKeyAsByte())
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        return Long.valueOf(claims.getId());
    }





}
