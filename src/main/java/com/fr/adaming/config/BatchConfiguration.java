package com.fr.adaming.config;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fr.adaming.config.listener.EtudiantListener;
import com.fr.adaming.config.processor.EtudiantProcessor;
import com.fr.adaming.config.reader.EtudiantReader;
import com.fr.adaming.config.writer.EtudiantWriter;
import com.fr.adaming.entity.Etudiant;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
    @Autowired
    JobLauncher jobLauncher;
	
	@Autowired
	public EtudiantReader etudiantReader;
	
	@Autowired
	public EtudiantWriter etudiantWriter;
	
	@Autowired
	public EtudiantProcessor etudiantProcessor;
	
	@Autowired
	public EtudiantListener etudiantListener;
	
	
	@Bean
	public Step etudiantStep() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
        return stepBuilderFactory.get("step1").<Etudiant, Etudiant>chunk(10)
                .faultTolerant()
                .skip(ValidationException.class)
                .skip(FlatFileParseException.class)
                .skip(ItemStreamException.class)
                .skipLimit(9)           
                .reader(etudiantReader.read())
                .processor(etudiantProcessor)
                .writer(etudiantWriter)
//                .listener(etudiantListener)
                .build();
	}
	
	@Scheduled(fixedDelay = 24*60*60*1000) // On va lancer cette méthode toutes les 30 secondes
    public void scheduleFixedDelayTask() throws Exception {

        System.out.println(" ########## Job lancé "+ new Date() + " ##########");
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        
        //importer le Job
        Job job = jobBuilderFactory
                .get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(etudiantStep())
                .end()
                .build();
        
        JobExecution execution = jobLauncher.run(job, param);
        System.out.println("Job finished with status :" + execution.getStatus());

    }

}
