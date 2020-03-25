package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.IMatiereService;

/**
 * Classe writer pour matiere
 * Implemente ItemWriter et redéfinit la méthode read
 * @author maxime
 *
 */
public class MatiereWriter implements ItemWriter<Matiere> {


	
	@Autowired
	private IMatiereService matiereService;

	/**
	 *Méthode write qui sauvagarde des données dans la DB
	 */
	@Override
	public void write(List<? extends Matiere> listeMatiere) {
		for (Matiere matiere : listeMatiere) {
			matiereService.create(matiere);
		}

	}
}
