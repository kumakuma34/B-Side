package bside.writing;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import bside.writing.*;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Controller
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
       return new MemberService(memberRepository());
   }

    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
// return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
