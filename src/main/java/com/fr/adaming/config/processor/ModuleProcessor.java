package com.fr.adaming.config.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Module;

/**
 * Classe Processor pour le step de MAJ des modules dans le job d'envoie email aux etudiants
 * Implmente ItemProcessor
 * @author maxime
 *
 */
@Component
public class ModuleProcessor implements ItemProcessor<Module, Module>{

	/**
	 *Méthode de process pour le traitement des données de module
	 * @return objet module
	 */
	@Override
	public Module process(Module item) throws Exception {
		//  In case you nedd a traitement
		return null;
	}

}
