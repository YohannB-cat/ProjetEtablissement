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

import com.fr.adaming.entity.Niveau;

/**
 * Reader de l'entité Niveau pour le SpringBatch
 * @author Flavien
 * @since 1.0.x
 *
 */

@EnableBatchProcessing
public class NiveauReader implements ItemReader<FlatFileItemReader<Niveau>> {
	
	@Value("classpath:/niveau.csv")
    private Resource inputResource;
	
	
	/**
	 * Methode Reader pour SpringBatch, extrait les données du fichiers niveau.csv et retourne les niveaux lus
	 * @return FlatFileItemReader-Niveau 
	 */
	@Bean
	public FlatFileItemReader<Niveau> read() {
		
		return new FlatFileItemReaderBuilder<Niveau>()
				.name("niveauItemReader")
				.linesToSkip(1)
				.resource(inputResource)
				.delimited()
				.names(new String[] {"classes", "id", "nom"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Niveau>() {{
					setTargetType(Niveau.class);
				}})
				.build();
	}
}
