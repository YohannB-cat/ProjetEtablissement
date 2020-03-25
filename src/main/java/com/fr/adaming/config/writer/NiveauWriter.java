package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dao.INiveauDao;
import com.fr.adaming.entity.Niveau;

/**
 * Writer de l'entité Niveau pour le SpringBatch
 * @author Flavien
 * @since 1.0.x
 *
 */
@Component
public class NiveauWriter implements ItemWriter<Niveau> {

	@Autowired
	private INiveauDao niveauDao;

	/**
	 * Methode write de la classe Niveau pour SpringBatch 
	 */
	@Override
	public void write(List<? extends Niveau> items) throws Exception {
		for (Niveau niveau : items) {
			niveauDao.save(niveau);
		}
	}
}
