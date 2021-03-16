package bside.writing.memberTokenService;

import bside.writing.Service.TokenService;
import bside.writing.Repository.MemberTokenRespository;
import bside.writing.dto.MemberTokenDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberTokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Autowired
    MemberTokenRespository memberTokenRespository;

    @Test
    public void 맴버토큰_저장() {
        MemberTokenDto sampleMemberDto = MemberTokenDto.builder()
                .id(1L)
                .accessToken("ac")
                .refreshToken("re")
                .build();
        tokenService.saveMemberToken(sampleMemberDto);
    }

    @Test
    public void 맴버토큰_삭제(){
        맴버토큰_저장();
        tokenService.deleteMemberToken(1L);
    }

}
