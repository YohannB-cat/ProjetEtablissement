package com.fr.adaming.converter;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class EtudiantConverterTest implements IConverterTest{
	
	@Autowired
	public IConverter<Etudiant, EtudiantDto> convert;

	@Override
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		EtudiantDto dto = new EtudiantDto("nom", "prenom", "adresse", 69003, "blabla", true, 19865156, 20510620, "email@ad.fr", true);
		
		Etudiant et = convert.dtoToEntite(dto);
		
		assertTrue(et.getAdresse().equals("adresse"));
		assertTrue(et.getPrenom().equals("prenom"));
		assertTrue(et.getNom().equals("nom"));
		assertTrue(et.getCodePostale()==69003);
		assertTrue(et.getVille().equals("blabla"));
		assertTrue(et.isSexe());
		assertTrue(et.isEnEtude());
		assertTrue(et.getCni()==19865156);
		assertTrue(et.getTelephone()==20510620);
		assertTrue(et.getEmail().equals("email@ad.fr"));
		
	}

	@Override
	public void testDtoToEntiteNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testDtoToEntiteNull_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entiteToDtoValid_shouldReturnEntite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entiteToDtoNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entiteToDtoNull_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listDtoToEntiteValid_shouldReturnEntite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listDtoToEntiteNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listDtoToEntiteNull_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listEntiteToDtoValid_shouldReturnEntite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listEntiteToDtoNotValid_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listEntiteToDtoNull_shouldReturnNull() {
		// TODO Auto-generated method stub
		
	}
	

	

}
