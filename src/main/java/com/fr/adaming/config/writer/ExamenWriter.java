package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.service.IExamenService;

/**
 * Classe Spring Batch Writer de l'entité Examen
 * @author Yohann
 * @since 1.0.x
 *
 */
public class ExamenWriter implements ItemWriter<Examen> {
	
	@Autowired
	private IExamenService service;

	/**
	 * Méthode qui écrit les données d'une liste d'examen dans la DB
	 * @param items Liste d'examen provenant de la classe ExamenProcessor
	 * @throws Exeception Lorsqu'il y a une erreur quelconque
	 */
	@Override
	public void write(List<? extends Examen> items) throws Exception {
		for (Examen exam : items) {
			service.create(exam);
		}
		
	}
	
	

}
