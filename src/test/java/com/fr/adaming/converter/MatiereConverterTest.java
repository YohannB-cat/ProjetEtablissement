package com.fr.adaming.converter;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereConverterTest {
	
	@Autowired
	public IConverter<Matiere, MatiereDto> convert;
	

	//TEST DTO TO ENTITE
	
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
	}
	
	@Test
	public void testDtoToEntiteNotValid_shouldReturnNull() {
		// TODO : pas de contrainte pour le moment
	}

	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));

	}
	
	//TEST LISTE DTO TO ENTITE
	
	@Test
	public void testListeDtoToEntite( ) {
		
	}
	
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		
	}
	
	@Test
	public void testListDtoToEntiteNotValid_shouldReturnNull() {
		
	}
	
	@Test
	public void testListDtoToEntiteNull_shouldReturnNull() {
		
	}
	
	//TEST  ENTITE TO DTO
	
	@Test
	public void testEntiteToDtoValid_shouldReturnEntite() {
		
	}
	
	@Test
	public void testEntiteToDtoNotValid_shouldReturnNull() {
		
	}
	
	@Test
	public void testEntiteToDtoNull_shouldReturnNull() {
		
	}
	
	//TEST LISTE ENTITE  TO DTO
	
	@Test
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		
	}
	
	@Test
	public void testListEntiteToDtoNotValid_shouldReturnNull() {
		
	}

	@Test
	public void testListEntiteToDtoNull_shouldReturnNull() {
		
	}
	
	
}
