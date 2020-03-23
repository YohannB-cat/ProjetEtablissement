package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class EtudiantConverterTest{

	@Autowired
	public IConverter<Etudiant, EtudiantDto> convert;

	
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		EtudiantDto dto = new EtudiantDto("nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);

		Etudiant et = convert.dtoToEntite(dto);

		assertTrue(et.getId()==0);
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

	
	@Test
	public void testDtoToEntiteNotValid_shouldReturnNull() {
		EtudiantDto dto = new EtudiantDto(null,null,null,0,null,false,0,0,null,false);
		Etudiant et = convert.dtoToEntite(dto);

		assertTrue(et.getId()==0);
		assertNull(et.getAdresse());
		assertNull(et.getPrenom());
		assertNull(et.getNom());
		assertTrue(et.getCodePostale() == 0);
		assertNull(et.getVille());
		assertFalse(et.isSexe());
		assertFalse(et.isEnEtude());
		assertTrue(et.getCni() == 0);
		assertTrue(et.getTelephone() == 0);
		assertNull(et.getEmail());
	}

	
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));

	}

	
	@Test
	public void testEntiteToDtoValid_shouldReturnEntite() {
		Etudiant et = new Etudiant(1,"nom","prenom","adresse","ville","email",69003,123,06,true,true);

		EtudiantDto dto = convert.entiteToDto(et);

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

	
	@Test
	public void testEntiteToDtoNotValid_shouldReturnNull() {
		Etudiant et = new Etudiant(0, null, null, null, null, null, 0, 0, 0, false, false);

		EtudiantDto dto = convert.entiteToDto(et);

		assertTrue(dto.getCodePostal() == 0);
		assertFalse(dto.isSexe());
		assertFalse(dto.isEnEtude());
		assertTrue(dto.getCni() == 0);
		assertTrue(dto.getTelephone() == 0);
		assertNull(dto.getAdresse());
		assertNull(dto.getEmail());
		assertNull(dto.getNom());
		assertNull(dto.getPrenom());
		assertNull(dto.getVille());

	}

	
	@Test
	public void testEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.entiteToDto(null));

	}

	
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<EtudiantDto> listedto = new ArrayList<EtudiantDto>();
		EtudiantDto dto = new EtudiantDto("nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);
		
		EtudiantDto dto2 = new EtudiantDto("nom2", "prenom2", "adresse2", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);
		listedto.add(dto);
		listedto.add(dto2);
		
		assertThat(convert.listDtoToEntite(listedto)).hasSize(2).hasOnlyElementsOfType(Etudiant.class);

	}

	
	@Test
	public void testListDtoToEntiteObjectNull_shouldReturnListObjectNull() {
		List<EtudiantDto> listedto = new ArrayList<EtudiantDto>();
		listedto.add(null);
		listedto.add(null);
		
		assertTrue((convert.listDtoToEntite(listedto).size()==2));
		assertNull((convert.listDtoToEntite(listedto).get(0)));
		assertNull((convert.listDtoToEntite(listedto).get(1)));
		
	}

	
	@Test
	public void testListDtoToEntiteNull_shouldReturnEmptyList() {
		assertTrue(convert.listDtoToEntite(null).isEmpty());

	}

	
	@Test
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Etudiant> liste = new ArrayList<Etudiant>();
		Etudiant et = new Etudiant(3, null, null, null, null, null, 0, 18, 0, false, false);
		Etudiant et2 = new Etudiant(8, null, null, null, null, null, 24, 0, 0, false, false);

		liste.add(et);
		liste.add(et2);
		
		assertThat(convert.listEntiteToDto(liste)).hasSize(2).hasOnlyElementsOfType(EtudiantDto.class);

	}

	
	@Test
	public void testListEntiteToDtoObjectNull_shouldReturnListObjectNull() {
		List<Etudiant> liste = new ArrayList<Etudiant>();
		liste.add(null);
		liste.add(null);
		
		assertTrue((convert.listEntiteToDto(liste).size()==2));
		assertNull((convert.listEntiteToDto(liste).get(0)));
		assertNull((convert.listEntiteToDto(liste).get(1)));

	}

	
	@Test
	public void testListEntiteToDtoNull_shouldReturnEmptyList() {
		assertTrue(convert.listEntiteToDto(null).isEmpty());

	}

}
