package com.fr.adaming.config.reader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Classe;

/**
 * Reader de l'entité Classe pour le SpringBatch
 * @author Flavien
 * @since 1.0.x
 *
 */

@EnableBatchProcessing
public class ClasseReader implements ItemReader<FlatFileItemReader<Classe>> {
	
	@Value("classpath:/classe.csv")
    private Resource inputResource;
	

	/**
	 * Methode Reader pour SpringBatch, extrait les données du fichiers classe.csv et retourne les classes lus
	 * @return FlatFileItemReader-Classe 
	 */
	@Bean
	public FlatFileItemReader<Classe> read() {
		
		return new FlatFileItemReaderBuilder<Classe>()
				.name("classeItemReader")
				.linesToSkip(1)
				.resource(inputResource)
				.delimited()
				.names(new String[] {"id", "nom", "etudiants"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Classe>() {
					{
					setTargetType(Classe.class);
					}
				}).build();
	}
}
