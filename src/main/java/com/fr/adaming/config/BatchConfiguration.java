package com.fr.adaming.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
                .build();
	}

}
