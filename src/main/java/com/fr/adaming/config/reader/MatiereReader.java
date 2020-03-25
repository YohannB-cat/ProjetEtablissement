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

import com.fr.adaming.entity.Matiere;

/**
 * Classe reader pour matière qui va lire un fichier et faire le mapping
 * Implemente l'interface ItemReader
 * @author maxime
 *
 */
public class MatiereReader implements ItemReader<FlatFileItemReader<Matiere>>{
	
	@Value ("classpath:/matiere.csv")
	private Resource inputRessource;

	/**
	 *Méthode read qui va recupérer les données à partir d'un fichier et renvoi un objet
	 *@return objet de type matière
	 */
	@Override
	public FlatFileItemReader<Matiere> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		return new FlatFileItemReaderBuilder<Matiere>()
				//attribuer un nom à notre ItemReader
				.name("matiereItemReader")
				.linesToSkip(1)
				.resource(inputRessource)
				.delimited()
				.names(new String[] {"id","nom"})
				//precise le type des objets pour le mapping
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Matiere>() {
					{
						setTargetType(Matiere.class);
						
					}
				}).build();
	}

}
