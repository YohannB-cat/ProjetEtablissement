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

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.entity.Examen;

public class ExamenConverterTest {

	@Autowired
	public IConverter<Examen, ExamenDto> convert;

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
	
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));
	}

	
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
	
	
	
	@Test
	public void testListDtoToEntiteEmpty_shouldReturnEmpty() {
		assertTrue((convert.listDtoToEntite(new ArrayList<ExamenDto>())).isEmpty());

	}

	
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

	
	@Test
	public void testListEntiteToDtoEmpty_shouldReturnEmpty() {
		assertTrue(convert.listEntiteToDto(new ArrayList<Examen>()).isEmpty());

	}


}
