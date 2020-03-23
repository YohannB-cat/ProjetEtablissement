package com.fr.adaming.converter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.entity.Etudiant;

/**
 * Classe test de la couche converter du dto EtudiantDtoCreate
 * @author clara
 * @since 1.0.X
 */
@SpringBootTest
public class EtudiantCreateConverterTest{

	@Autowired
	public IConverter<Etudiant, EtudiantDtoCreate> convert;

	/**
	 * Vérification conversion de dto à entite ok
	 */
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		EtudiantDtoCreate dto = new EtudiantDtoCreate(19,"nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);

		Etudiant et = convert.dtoToEntite(dto);

		assertTrue(et.getId()==19);
		assertTrue(et.getAdresse().equals("adresse"));
		assertTrue(et.getPrenom().equals("prenom"));
		assertTrue(et.getNom().equals("nom"));
		assertTrue(et.getCodePostale() == 69003);
		assertTrue(et.getVille().equals("blabla"));
		assertTrue(et.isSexe());
		assertTrue(et.isEnEtude());
		assertTrue(et.getCni() == 19865156);
		assertTrue(et.getTelephone() == 20510620);
		assertTrue(et.getEmail().equals("email@ad.fr"));

	}

	/**
	 * Vérification conversion de dto null retourne une entite null 
	 */
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));

	}
	/**
	 * Vérification conversion de entite à dto ok
	 */
	@Test
	public void testEntiteToDtoValid_shouldReturnEntite() {
		Etudiant et = new Etudiant(1,"nom","prenom","adresse","ville","email",69003,123,06,true,true);

		EtudiantDtoCreate dto = convert.entiteToDto(et);

		
		assertTrue(dto.getId()==1);
		assertTrue(dto.getAdresse().equals("adresse"));
		assertTrue(dto.getPrenom().equals("prenom"));
		assertTrue(dto.getNom().equals("nom"));
		assertTrue(dto.getCodePostal() == 69003);
		assertTrue(dto.getVille().equals("ville"));
		assertTrue(dto.isSexe());
		assertTrue(dto.isEnEtude());
		assertTrue(dto.getCni() == 123);
		assertTrue(dto.getTelephone() == 06);
		assertTrue(dto.getEmail().equals("email"));

	}

	/**
	 * Vérification conversion de entite null retourne dto null
	 */
	@Test
	public void testEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.entiteToDto(null));

	}

	/**
	 * Vérification conversion listeDto à liste entité ok
	 */
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<EtudiantDtoCreate> listedto = new ArrayList<EtudiantDtoCreate>();
		EtudiantDtoCreate dto = new EtudiantDtoCreate(15, "nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);
		
		EtudiantDtoCreate dto2 = new EtudiantDtoCreate(18,"nom2", "prenom2", "adresse2", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);
		listedto.add(dto);
		listedto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listedto).size()==2));

	}

	/**
	 * Vérification conversion listeDto avec dtos null retourne une liste entité avec entités nulles
	 */
	@Test
	public void testListDtoToEntiteObjectNull_shouldReturnListObjectNull() {
		List<EtudiantDtoCreate> listedto = new ArrayList<EtudiantDtoCreate>();
		listedto.add(null);
		listedto.add(null);
		assertTrue(((convert.listDtoToEntite(listedto)).size()==2));
		assertNull(convert.listDtoToEntite(listedto).get(0));
		assertNull(convert.listDtoToEntite(listedto).get(1));
		

	}

	/**
	 * Vérification conversion listeDto null retourne liste entite vide 
	 */
	@Test
	public void testListDtoToEntiteNull_shouldReturnEmptyList() {
		assertTrue(convert.listDtoToEntite(null).isEmpty());

	}

	/**
	 * Vérification conversion listeEntite à listeDto ok
	 */
	@Test
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Etudiant> liste = new ArrayList<Etudiant>();
		Etudiant et = new Etudiant(3, null, null, null, null, null, 0, 18, 0, false, false);
		Etudiant et2 = new Etudiant(8, null, null, null, null, null, 24, 0, 0, false, false);

		liste.add(et);
		liste.add(et2);
		
		assertTrue((convert.listEntiteToDto(liste).size()==2));

	}

	/**
	 * Vérification conversion liste entité avec entités null retourne une listeDto avec dtos null
	 */
	@Test
	public void testListEntiteToDtoObjectNull_shouldReturnListObjectNull() {
		List<Etudiant> liste = new ArrayList<Etudiant>();
		liste.add(null);
		liste.add(null);
		assertTrue((convert.listEntiteToDto(liste).size()==2));
		assertNull(convert.listEntiteToDto(liste).get(0));
		assertNull(convert.listEntiteToDto(liste).get(1));

	}

	/**
	 * Vérification conversion listeEntite null retourne listeDto vide
	 */
	@Test
	public void testListEntiteToDtoNull_shouldReturnEmptyList() {
		assertTrue(convert.listEntiteToDto(null).isEmpty());

	}

}
