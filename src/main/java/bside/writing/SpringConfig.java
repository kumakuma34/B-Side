package bside.writing;

import bside.writing.Member.Member;
import bside.writing.Repository.JpaMemberRepository;
import bside.writing.Repository.MemberRepository;
import bside.writing.Service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import bside.writing.*;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Controller
@ComponentScan
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
}
