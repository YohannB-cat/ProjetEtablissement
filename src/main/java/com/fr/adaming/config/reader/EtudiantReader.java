package com.fr.adaming.config.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Etudiant;

/**
 * Classe Reader relative à l'entité Etudiant
 * @author clara
 * @since 1.0.x
 */
@Component
public class EtudiantReader implements ItemReader<FlatFileItemReader<Etudiant>>{
	
    @Value("${FichierEtudiant}")
    @Autowired
    private Resource inputResource;

	@Override
	public FlatFileItemReader<Etudiant> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
		return new FlatFileItemReaderBuilder<Etudiant>()
        		//attribuer un name à notre ItemReader
                .name("personItemReader")
				// On ignore la première ligne du ficher persons.csv
                .linesToSkip(1)
                .resource(inputResource)
                .delimited()
                .names(new String[]{"id", "nom", "prenom", "email"})
				//Préciser le type des objets pour le mapping <Person>
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Etudiant>() {{
                    setTargetType(Etudiant.class);

                }})
                .build();
	}

}
