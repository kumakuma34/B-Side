package bside.writing.Service;

import bside.writing.Member.Member;
import bside.writing.Repository.MemberRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public TokenService(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    // TODO optional
    public String getAccessToken(String idTokenString) throws GeneralSecurityException, IOException {

        GoogleIdToken idToken = (getVerify(idTokenString));
        if(idToken != null){
            GoogleIdToken.Payload payload = idToken.getPayload();

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
        else{
            return "Invalid id_token";
        }
    }

    public GoogleIdToken getVerify(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        return verifier.verify(idTokenString);
    }

    public void saveOrUpdateBy(String email, String name, String pictureUrl){
        if(memberRepository.findByUserEmail(email).isEmpty()){
            // TODO memberRepository. 정보 비교 메서드 없음, 이름과 picture URL이 바뀌었다면 update해줘야함
            // memberService.update
            }
        else{
            // TODO member 객체를 어떻게 넘길 것인지 + Role 관련
            memberService.join(new Member());
        }
    }

    public String makeAccessToken(String email){
        return email;
    }
}
