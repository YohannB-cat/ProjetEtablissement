package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.entity.Examen;
/**
 * Classe test de la conversion des données entre Examen et ExamenDto
 * @author Yohann
 * @since 1.0.x
 *
 */
@SpringBootTest
public class ExamenConverterTest {

	@Autowired
	public IConverter<Examen, ExamenDto> convert;

	/**
	 * Test de la conversion d'une dto valide en entité
	 * Doit retourner une entité valide
	 */
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		String date = "2020-02-20";
		LocalDate localDate = LocalDate.parse(date);
		ExamenDto dto = new ExamenDto(localDate, "type",2.2d);

		Examen abs = convert.dtoToEntite(dto);

		assertNotNull(abs);
		assertEquals(localDate, abs.getDate());
		assertTrue(abs.getDate().equals(localDate));
		assertTrue(abs.getType().equals("type"));
		assertTrue(abs.getCoefficient()==2.2d);
	}
	
	/**
	 * Test de la conversion d'une dto null à une entité
	 * Doit retourner null
	 */
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));
	}

	
	/**
	 * Test de la conversion d'une entité valide en dto
	 * Doit retourner un dto valide
	 */
	@Test
	public void testEntiteToDtoValid_shouldReturnEntite() {
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Examen exa = new Examen(1,lDate, "type",2.2d);

		ExamenDto dto = convert.entiteToDto(exa);

		assertNotNull(dto);
		assertTrue(dto.getDate().toString().equals(date));
		assertTrue(dto.getType().equals("type"));
		assertTrue(dto.getCoefficient()==2.2d);

	}

	
	/**
	 * Test de la conversion d'une liste de dto valides en liste d'entités valides
	 * Doit retourner une liste d'entités
	 */
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<ExamenDto> listeDto = new ArrayList<ExamenDto>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		ExamenDto dto = new ExamenDto( lDate, "justif", 2.2d);
		ExamenDto dto2 = new ExamenDto(lDate, "justif2", 2.3d);
		
		listeDto.add(dto);
		listeDto.add(dto2);
	
		assertThat(convert.listDtoToEntite(listeDto)).hasSize(2).hasOnlyElementsOfType(Examen.class);
	}
	
	
	
	/**
	 * Test de la conversion d'une liste de dto vide en liste d'entité
	 * Doit retourner une liste vide
	 */
	@Test
	public void testListDtoToEntiteEmpty_shouldReturnEmpty() {
		assertTrue((convert.listDtoToEntite(new ArrayList<ExamenDto>())).isEmpty());

	}

	
	/**
	 * Test de la conversion d'une liste d'entités valides en liste de dto
	 * Doit retourner une liste de dto valides
	 */
	@Test
	public void testListEntiteToDtoValid_shouldReturnDto() {
		List<Examen> liste = new ArrayList<Examen>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Examen exa = new Examen(1, lDate, "justif", 2.2d);
		Examen exa2 = new Examen(2,lDate, "justif2", 2.3d);

		liste.add(exa);
		liste.add(exa2);
		
		List<ExamenDto> listeReturned = convert.listEntiteToDto(liste);
		
		assertThat(listeReturned).hasSize(2).hasOnlyElementsOfType(ExamenDto.class);

	}

	
	/**
	 * Test de la conversion d'une liste d'entités vide en liste de dto
	 * Doit retourner une liste vide
	 */
	@Test
	public void testListEntiteToDtoEmpty_shouldReturnEmpty() {
		assertTrue(convert.listEntiteToDto(new ArrayList<Examen>()).isEmpty());

	}


}
