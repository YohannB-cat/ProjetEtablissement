package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.service.EtudiantService;

/**
 * Classe Writer relative à l'entité Etudiant
 * @author clara
 * @since 1.0.x
 */
public class EtudiantWriter implements ItemWriter<Etudiant>{

	@Autowired
	private EtudiantService etserv;
	
	/**
	 * Permet l'écriture dans la database via la couche service. Tente d'abord une modification, au cas où l'entité existerait déjà (testée par id) puis une création sinon
	 */
	@Override
	public void write(List<? extends Etudiant> items) throws Exception {
		for (Etudiant et : items) {
			if(!etserv.update(et)) {
				etserv.create(et);
			}
			
		}
		
	}
	

}
