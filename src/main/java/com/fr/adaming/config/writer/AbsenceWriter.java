package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.service.IAbsenceService;

/**
 * Cette classe sert de Writer(SpringBatch) pour l'entité Absence
 * @author Isaline
 * @since 1.0.X
 */
@Component
public class AbsenceWriter implements ItemWriter<Absence>{
	
	@Autowired
	private IAbsenceService absenceService;

	/**
	 * Cette méthode entre les nouvelles absences du fichier csv des absences dans la BD
	 * Elle tente une modification si l'instance existe déjà. Sinon, elle la crée. 
	 * @param items liste d'objets de type absence ou héritant du type absence
	 */
	@Override
	public void write(List<? extends Absence> items) throws Exception {
		for (Absence absence : items) {
			boolean update = absenceService.update(absence);
			if (!update) {
				absenceService.create(absence);
			}
		}
	}
}
