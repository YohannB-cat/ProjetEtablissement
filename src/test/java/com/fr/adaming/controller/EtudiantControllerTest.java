package com.fr.adaming.controller;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.entity.Etudiant;


public class EtudiantControllerTest {
	
	@Autowired
	private IEtudiantController controller;
	
	@Test
	@DisplayName("Création d'un etudiant null")
	public void testCreatingNullEtudiant_shouldReturnNull() {
		assertNull(controller.create(null));
	}

}
