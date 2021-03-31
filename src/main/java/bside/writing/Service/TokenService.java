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
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.html.Option;
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

    // google library method
    public MemberDto getMemberDto(final String idTokenString) throws GeneralSecurityException, IOException{
        GoogleIdToken idToken = Optional.ofNullable(getTokenAfterVerify(idTokenString))
                .orElseThrow(()-> new MalformedJwtException(""));
        GoogleIdToken.Payload payload = idToken.getPayload();

        return MemberDto.builder()
                .email(payload.getEmail())
                .nickName(payload.get("name").toString())
                .profileUrl(payload.get("picture").toString())
                //TODO : handle userRole using Spring security
                .userRole("ROLE_USER")
                .build();
    }

    // google library method
    public GoogleIdToken getTokenAfterVerify(final String idTokenString) throws GeneralSecurityException, IOException {
        return new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build()
                .verify(idTokenString);
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

    public byte[] generateKeyAsByte(){
        return DatatypeConverter.parseBase64Binary(JWT_SECRET);
    }

    @Transactional
    public String refreshAccessToken(final String refreshToken){
        memberTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new MalformedJwtException("invalid refresh token"));

        final Long memberId = getUid(refreshToken);
        final String accessToken = makeAccessToken(memberId);

        MemberTokenDto memberTokenDto = MemberTokenDto.builder()
                .id(memberId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        memberTokenRepository.save(memberTokenDto.toEntity());

        return accessToken;
    }

    public MemberTokenDto saveMemberToken(MemberTokenDto memberTokenDto){
        MemberToken entity = memberTokenDto.toEntity();
        return new MemberTokenDto(memberTokenRepository.save(entity));
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

    public Long getUid(final String accessToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(generateKeyAsByte())
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        return Long.valueOf(claims.getId());
    }

}
