package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class ClasseConverterTest {

	@Autowired
	public IConverter<Classe, ClasseDto> convert;

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite correct")
	public void testDtoToEntiteValid_shouldReturnEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		ClasseDto dto = new ClasseDto("TopOfTheTop", listEtudiant);

		Classe retour = convert.dtoToEntite(dto);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getEtudiants());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite avec listNull")
	public void testDtoToEntiteWithNullClasseList_shouldReturnEntite() {
		ClasseDto dto = new ClasseDto("TopOfTheTop", null);

		Classe retour = convert.dtoToEntite(dto);

		assertNotNull(retour);
		assertEquals(null, retour.getEtudiants());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite avec nomNull")
	public void testDtoToEntiteWithNullNom_shouldReturnEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		ClasseDto dto = new ClasseDto(null, listEtudiant);

		Classe retour = convert.dtoToEntite(dto);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getEtudiants());
		assertEquals(null, retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion DtoToEntite dtoNull")
	public void testDtoToEntiteWithNullEntite_shouldReturnNull() {
		ClasseDto dto = null;

		Classe retour = convert.dtoToEntite(dto);

		assertNull(retour);
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto correct")
	public void testEntiteToDtoValid_shouldReturnDto() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		Classe entite = new Classe(1, "TopOfTheTop", listEtudiant);

		ClasseDto retour = convert.entiteToDto(entite);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getListe());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto avec listNull")
	public void testEntiteToDtoWithNullClasseList_shouldReturnDto() {
		Classe entite = new Classe(1, "TopOfTheTop", null);

		ClasseDto retour = convert.entiteToDto(entite);

		assertNotNull(retour);
		assertEquals(null, retour.getListe());
		assertEquals("TopOfTheTop", retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto avec nomNull")
	public void testEntiteToDtoWithNullNom_shouldReturnDto() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		Classe entite = new Classe(1, null, listEtudiant);

		ClasseDto retour = convert.entiteToDto(entite);

		assertNotNull(retour);
		assertEquals(listEtudiant, retour.getListe());
		assertEquals(null, retour.getNom());
	}

	// Valide !
	@Test
	@DisplayName("Convertion EntiteToDto dtoNull")
	public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
		Classe entite = null;

		ClasseDto retour = convert.entiteToDto(entite);

		assertNull(retour);
	}

	// Valide !
	@Test
	@DisplayName("Convertion ListDtoToListEntite correct")
	public void testListDtoToListEntiteValid_shouldReturnListEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		ClasseDto dto1 = new ClasseDto("TopOfTheTop", listEtudiant);
		ClasseDto dto2 = new ClasseDto("Moins Top", listEtudiant);
		List<ClasseDto> listClasseDto = new ArrayList<ClasseDto>();
		listClasseDto.add(dto1);
		listClasseDto.add(dto2);

		List<Classe> listRetour = convert.listDtoToEntite(listClasseDto);

		assertNotNull(listRetour);
		assertThat(listRetour).hasSize(2);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("etudiants", listEtudiant);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("nom", "TopOfTheTop");
	}

	// Valide !
	@Test
	@DisplayName("Convertion ListDtoToListEntite correct")
	public void testListDtoToListEntiteWithNullList_shouldReturnNull() {
		List<ClasseDto> listClasseDto = null;

		List<Classe> listRetour = convert.listDtoToEntite(listClasseDto);

		assertNull(listRetour);
	}

	// Valide !
	@Test
	@DisplayName("Convertion listEntiteToListDto correct")
	public void testListEntiteToListDtoValid_shouldReturnListEntite() {
		Etudiant e1 = new Etudiant();
		Etudiant e2 = new Etudiant();
		List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant.add(e1);
		listEtudiant.add(e2);
		Classe entite1 = new Classe(1, "TopOfTheTop", listEtudiant);
		Classe entite2 = new Classe(2, "Moins Top", listEtudiant);
		List<Classe> listClasse = new ArrayList<Classe>();
		listClasse.add(entite1);
		listClasse.add(entite2);

		List<ClasseDto> listRetour = convert.listEntiteToDto(listClasse);

		assertNotNull(listRetour);
		assertThat(listRetour).hasSize(2);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("liste", listEtudiant);
		assertThat(listRetour.get(0)).hasFieldOrPropertyWithValue("nom", "TopOfTheTop");
	}

	// Valide !
	@Test
	@DisplayName("Convertion ListDtoToListEntite null")
	public void testListEntiteToListDtoWithNullList_shouldReturnNull() {
		List<Classe> listClasse = null;

		List<ClasseDto> listRetour = convert.listEntiteToDto(listClasse);

		assertNull(listRetour);
	}

}
