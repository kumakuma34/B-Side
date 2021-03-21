package bside.writing.themeTest;

import bside.writing.Service.ThemeService;
import bside.writing.dto.ThemeDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ThemeServiceTest {

    @Autowired
    private ThemeService themeService;

    @Test
    public void 랜덤_글감_가져오기(){
        int n = 3;
        List<ThemeDto> nRandomTheme = themeService.getNRandomTheme(n);
        Assertions.assertThat(nRandomTheme.size()).isEqualTo(n);
    }
}
