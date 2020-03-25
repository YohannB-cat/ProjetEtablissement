package com.fr.adaming.config.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Absence;

/**
 * Cette classe sert de processeur(SpringBatch) pour l'entité absence
 * @author Isaline
 * @since 1.0.X
 *
 */
@Component
public class AbsenceProcessor implements ItemProcessor<Absence, Absence> {

	/**
	 * Méthode à implémenter si besoin
	 * @param item objet de type Absence
	 */
	@Override
	public Absence process(Absence item) throws Exception {
		return null;
	}
}
