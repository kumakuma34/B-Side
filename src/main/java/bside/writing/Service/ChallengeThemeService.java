package bside.writing.Service;

import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.ChallengeThemeRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.domain.challenge.ChallengeTheme;
import bside.writing.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeThemeService {
    private final ChallengeThemeRepository challengeThemeRepository;

    public List<String> insertChallengeTheme(String input , Long Challenge_id){
        String[] result = input.split(" ");
        for(int i = 0 ; i < result.length; i++){

        }
        List<String> empty = Arrays.asList("1");
        return empty;

    }
}
