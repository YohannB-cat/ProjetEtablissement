package com.fr.adaming.config.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Absence;

/**
 * Cette classe sert de Reader(SpringBatch) pour l'entité Absence
 * @author Isaline
 * @since 1.0.X
 */
@Component
public class AbsenceReader {
	
	@Value("classpath:/Absence.csv")
	private Resource inputResource;

	/**
	 * Cette méthode lit le fichier csv des absences  
	 * @return FlatFileItemReader objet de type Absence 
	 */
	public FlatFileItemReader<Absence> readerAbsence() {
	        return new FlatFileItemReaderBuilder<Absence>()
	                .name("absenceItemReader")
	                .linesToSkip(1)
	                .resource(inputResource)
	                .delimited()
	                .names(new String[]{"id", "début", "fin", "justification", "description", "étudiant"})
					//Préciser le type des objets pour le mapping
	                .fieldSetMapper(new BeanWrapperFieldSetMapper<Absence>() {{
	                    setTargetType(Absence.class);

	                }})
	                .build();
	    }
}
