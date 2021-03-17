package bside.writing.Service;

import bside.writing.Repository.ChallengeRepository;
import bside.writing.Repository.ChallengeThemeRepository;
import bside.writing.domain.challenge.Challenge;
import bside.writing.domain.challenge.ChallengeTheme;
import bside.writing.dto.ChallengeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeThemeService {
    private final ChallengeThemeRepository challengeThemeRepository;

    public ArrayList<String> insertChallengeTheme(String input , Long Challenge_id){
        String[] result = input.split(" ");
        ArrayList<String> processedData = new ArrayList<>();
        for(int i = 0 ; i < result.length; i++){
            System.out.println(result[i]);
            processedData.add(result[i].substring(1,result[i].length()));
            //challengeThemeRepository.save(ChallengeTheme.builder().challenge_id(Challenge_id).theme_name(result[i]).build());
        }
        for(int i = 0 ; i<processedData.size(); i++){
            challengeThemeRepository.save(ChallengeTheme.builder().challenge_id(Challenge_id).theme_name(processedData.get(i)).build());
        }
        return processedData;

    }
}
