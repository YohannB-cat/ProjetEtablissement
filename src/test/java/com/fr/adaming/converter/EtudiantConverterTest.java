package com.fr.adaming.converter;

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
public class EtudiantConverterTest implements IConverterTest {

	@Autowired
	public IConverter<Etudiant, EtudiantDto> convert;

	@Override
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		EtudiantDto dto = new EtudiantDto("nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);

		Etudiant et = convert.dtoToEntite(dto);

		assertTrue(et.getId()!=0);
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

	@Override
	@Test
	public void testDtoToEntiteNotValid_shouldReturnNull() {
		// TODO : pas de contrainte pour le moment
	}

	@Override
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));

	}

	@Override
	@Test
	public void testEntiteToDtoValid_shouldReturnEntite() {
		Etudiant et = new Etudiant(0, null, null, null, null, null, 0, 0, 0, false, false);

		EtudiantDto dto = convert.entiteToDto(et);

		assertTrue(dto.getAdresse().equals("adresse"));
		assertTrue(dto.getPrenom().equals("prenom"));
		assertTrue(dto.getNom().equals("nom"));
		assertTrue(dto.getCodePostal() == 69003);
		assertTrue(dto.getVille().equals("blabla"));
		assertTrue(dto.isSexe());
		assertTrue(dto.isEnEtude());
		assertTrue(dto.getCni() == 19865156);
		assertTrue(dto.getTelephone() == 20510620);
		assertTrue(dto.getEmail().equals("email@ad.fr"));

	}

	@Override
	@Test
	public void testEntiteToDtoNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.entiteToDto(null));

	}

	@Override
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<EtudiantDto> listedto = new ArrayList<EtudiantDto>();
		EtudiantDto dto = new EtudiantDto("nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);
		
		EtudiantDto dto2 = new EtudiantDto("nom2", "prenom2", "adresse2", 69003, "blabla", true, 19865156, 20510620,
				"email@ad.fr", true);
		listedto.add(dto);
		listedto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listedto).size()==2));

	}

	@Override
	@Test
	public void testListDtoToEntiteNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testListDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.listDtoToEntite(null));

	}

	@Override
	@Test
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Etudiant> liste = new ArrayList<Etudiant>();
		Etudiant et = new Etudiant(3, null, null, null, null, null, 0, 18, 0, false, false);
		Etudiant et2 = new Etudiant(8, null, null, null, null, null, 24, 0, 0, false, false);

		liste.add(et);
		liste.add(et2);
		
		assertTrue((convert.listEntiteToDto(liste).size()==2));

	}

	@Override
	@Test
	public void testListEntiteToDtoNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testListEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.listEntiteToDto(null));

	}

}
