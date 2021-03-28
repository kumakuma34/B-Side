package bside.writing.Service;

import bside.writing.domain.member.MemberToken;
import bside.writing.Repository.MemberTokenRepository;
import bside.writing.dto.MemberDto;
import bside.writing.dto.MemberTokenDto;
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
import javax.transaction.Transactional;
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
    @Value("${REFRESH_TOKEN_LIFETIME}")
    private int REFRESH_TOKEN_LIFETIME;

    private final MemberTokenRepository memberTokenRepository;

    public MemberDto getMemberDto(String idTokenString) throws GeneralSecurityException, IOException, AuthenticationException {

        GoogleIdToken idToken = Optional.ofNullable(getVerify(idTokenString))
                .orElseThrow(() -> new AuthenticationException(""));
        GoogleIdToken.Payload payload = idToken.getPayload();

        String emailByToken = payload.getEmail();
        String nickNameByToken = payload.get("name").toString();
        String profileUrlByToken = payload.get("picture").toString();

        MemberDto memberDto = MemberDto.builder()
                .email(emailByToken)
                .nickName(nickNameByToken)
                .profileUrl(profileUrlByToken)
                .userRole("ROLE_USER")
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

    public String makeAccessToken(Long memberId){
        return makeJwtToken(memberId, ACCESS_TOKEN_LIFETIME);
    }

    public String makeRefreshToken(Long memberId){
        return makeJwtToken(memberId, REFRESH_TOKEN_LIFETIME);
    }

    public String makeJwtToken(Long memberId, int tokenLifeTime){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        final Key signingKey = new SecretKeySpec(generateKeyAsByte(), signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setId(String.valueOf(memberId))
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + tokenLifeTime))
                .compact();
    }

    public Long getUid(String accessToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(generateKeyAsByte())
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        return Long.valueOf(claims.getId());
    }

    public MemberTokenDto saveMemberToken(MemberTokenDto memberTokenDto){
        MemberToken entity = memberTokenDto.toEntity();
        memberTokenRepository.save(entity);
        return new MemberTokenDto(entity);
    }

    @Transactional
    public MemberTokenDto updateMemberToken(Long memberId, String accessToken){
        MemberToken memberToken = memberTokenRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());
        memberToken.update(accessToken);
        return new MemberTokenDto(memberToken);
    }

    @Transactional
    public void deleteMemberToken(Long memberId){
        try{
            memberTokenRepository.deleteById(memberId);
        }
        catch (Exception e){
            throw new IllegalArgumentException("no such user");
        }
    }

    @Transactional
    public String refreshAccessToken(String refreshToken){
        MemberToken memberToken = memberTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NoSuchElementException());

        String accessToken = makeAccessToken(getUid(refreshToken));

        MemberTokenDto memberTokenDto = MemberTokenDto.builder()
                .id(getUid(refreshToken))
                .accessToken(accessToken)
                .refreshToken(refreshToken).build();
        memberTokenRepository.save(memberTokenDto.toEntity());
        return accessToken;
    }
}
