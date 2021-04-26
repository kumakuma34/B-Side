package bside.writing.jobs;

import bside.writing.tasklets.ChallengeTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChallengeStatusJob {
    private final String BATCH_NAME = "ChallengeStatusJob_";

    private final JobBuilderFactory jobBuilderFactory; // Job 빌더 생성용
    private final StepBuilderFactory stepBuilderFactory; // Step 빌더 생성용

    @Bean
    public Job job(){
        return jobBuilderFactory.get(BATCH_NAME)
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get(BATCH_NAME + "Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(BATCH_NAME + "Step1 Started");return RepeatStatus.FINISHED; }).build();

    }
}
