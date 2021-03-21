package bside.writing.themeTest;

import bside.writing.Repository.ThemeRepository;
import bside.writing.domain.theme.Theme;
import bside.writing.dto.ThemeDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ThemeRepositoryTest {

    @Autowired
    ThemeRepository themeRepository;

    @Test
    public void 글감_저장(){
        Theme entity = ThemeDto.builder()
                .name("쿠마쿠")
                .category("배신장르")
                .build()
                .toEntity();
        themeRepository.save(entity);
    }

    @Test
    public void 글감_랜덤으로_가져오기(){
        int n = 3;
        List<Theme> nthRandomTheme = themeRepository.getNRandomTheme(n);
        Assertions.assertThat(nthRandomTheme.size()).isEqualTo(n);
    }
}
