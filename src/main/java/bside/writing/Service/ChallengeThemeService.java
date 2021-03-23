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

    //글감 한개당 10글자 제한
    //글감 최대 3
    //#서평 #일기 #기록

    public String insertChallengeTheme(String input , Long Challenge_id){
        String[] result = input.split(" ");
        if(result.length > 3) return "challengeTheme count out of limit"; //금감 최대 갯수 3개 제한

        ArrayList<String> processedData = new ArrayList<>();
        for(int i = 0 ; i < result.length; i++){
            if(result[i].length() > 10) return "length out of limit"; //글감 글자 수 10글자 제한
            processedData.add(result[i].substring(1,result[i].length()));
        }
        for(int i = 0 ; i<processedData.size(); i++){
            challengeThemeRepository.save(ChallengeTheme.builder().challenge_id(Challenge_id).theme_name(processedData.get(i)).build());
        }
        return "Success";//성공
    }

    public List<String> getThemeByChallengeId(Long id){
        return challengeThemeRepository.findByChallengeId(id);
    }
}
