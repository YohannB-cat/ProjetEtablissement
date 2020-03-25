package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dao.IClasseDao;
import com.fr.adaming.entity.Classe;

/**
 * Writer de l'entité Classe pour le SpringBatch
 * @author Flavien
 * @since 1.0.x
 *
 */
@Component
public class ClasseWriter implements ItemWriter<Classe> {
	
	@Autowired
	private IClasseDao classeDao;

	/**
	 * Methode write de la classe Classe pour SpringBatch 
	 */
	@Override
	public void write(List<? extends Classe> items) throws Exception {
		for (Classe classe : items) {
			classeDao.save(classe);
		}
	}
}
