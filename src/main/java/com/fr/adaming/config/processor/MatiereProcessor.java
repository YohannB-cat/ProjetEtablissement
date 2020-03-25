package com.fr.adaming.config.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Matiere;

/**
 * Classe Processor pour le step de MAJ des matières dans le job d'envoie email aux etudiants
 * Implmente ItemProcessor
 * @author maxime
 *
 */
@Component
public class MatiereProcessor implements ItemProcessor<Matiere, Matiere>{

	/**
	 * Méthode de process pour le traitement des données de matières
	 * @return objet matiere
	 */
	@Override
	public Matiere process(Matiere item) throws Exception {
		// In case you nedd a traitement
		return null;
	}

}
