package com.fr.adaming.config.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Examen;


/**
 * Classe Spring Batch Reader de l'entité examen qui lit un fichier csv placé dans le dossier ressource
 * et qui envoie un objet examen à  la classe processor
 * @author Yohann
 * @since 1.0.x
 *
 */
@Component
public class ExamenReader implements ItemReader<FlatFileItemReader<Examen>> {
	
	@Value("${FichierExamens}")
	private Resource inputResource;

	@Override
	public FlatFileItemReader<Examen> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return new FlatFileItemReaderBuilder<Examen>()
				.name("examenItemReader")
				.linesToSkip(1)
				.resource(inputResource)
				.delimited()
				.names(new String[] {"identifiant","dateExam","nomExam","coefExam"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Examen>() {{setTargetType(Examen.class);}})
				.build();
	}

}
