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

import com.fr.adaming.entity.Module;

/**
 *  Classe reader pour module qui va lire un fichier et faire le mapping
 * Implemente l'interface ItemReader
 * @author maxime
 *
 */
@Component
public class ModuleReader implements ItemReader<FlatFileItemReader<Module>>{

	@Value ("classpath:/module.csv")
	private Resource inputRessource;
	
	/**
	 *Méthode read qui va recupérer les données à partir d'un fichier et faire le maping vers un objet Java
	 *@return objet de type flatfileItemReader de l'entité module
	 */
	@Override
	public FlatFileItemReader<Module> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return new FlatFileItemReaderBuilder<Module>()
				.name("moduleItemReader")
				.linesToSkip(1)
				.resource(inputRessource)
				.delimited()
				.names(new String[] {"id","nom","matieres"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Module>() {
					{
						setTargetType(Module.class);
					}
				}).build();
	}

}
