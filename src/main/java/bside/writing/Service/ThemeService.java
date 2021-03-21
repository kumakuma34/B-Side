package bside.writing.Service;

import bside.writing.Repository.ThemeRepository;
import bside.writing.dto.ThemeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public List<ThemeDto> getNRandomTheme(int n){
        final int MAX_COUNT = 10;
        if(n > MAX_COUNT){
            throw new IllegalArgumentException();
        }

        List<ThemeDto> randomThemeList = themeRepository.getNRandomTheme(n)
                .stream().map(entity -> new ThemeDto(entity)).collect(Collectors.toList());
        return randomThemeList;
    }
}
