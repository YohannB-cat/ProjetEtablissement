package com.fr.adaming.config.processor;

import org.springframework.batch.item.ItemProcessor;

import com.fr.adaming.entity.Matiere;

public class MatiereProcessor implements ItemProcessor<Matiere, Matiere>{

	@Override
	public Matiere process(Matiere item) throws Exception {
		// In case you nedd a traitement
		return null;
	}

}
