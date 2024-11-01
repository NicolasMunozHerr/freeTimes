package com.batch.config;

import com.batch.entities.Person;
import com.batch.steps.PersonItemProcessor;
import com.batch.steps.PersonItemReader;
import com.batch.steps.PersonItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  private PlatformTransactionManager transactionManager;
  @Autowired
  private JobRepository jobRepository;
  @Bean
  public PersonItemReader itemReader(){
    return new PersonItemReader();
  }

  @Bean
  public PersonItemProcessor itemProcessor(){
    return new PersonItemProcessor();
  }
  @Bean
  public PersonItemWriter itemWriter(){
    return new PersonItemWriter();
  }


  @Bean
  public Step readFile(){
    return new StepBuilder("readFile", jobRepository )
        .<Person, Person>chunk(10, transactionManager)
        .reader(itemReader())
        .processor(itemProcessor())
        .writer(itemWriter())
        .build();
  }


  @Bean
  public Job job() {
    return new JobBuilder("importUserJob", jobRepository)
        .start(readFile())
        .build();
  }
}
