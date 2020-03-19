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

import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class AbsenceConverterTest {

	@Autowired
	public IConverter<Absence, AbsenceDto> convert;

	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		Etudiant etu = new Etudiant();
		AbsenceDto dto = new AbsenceDto("2020-02-20", "2020-02-20","justif","desc", etu);
		String date = "2020-02-20";
		LocalDate localDate = LocalDate.parse(date);

		Absence abs = convert.dtoToEntite(dto);

		assertNotNull(abs);
		assertEquals(localDate, abs.getDebut());
		assertTrue(abs.getFin().equals(localDate));
		assertTrue(abs.getEtudiant().equals(etu));
		assertTrue(abs.getJustification().equals("justif"));
		assertTrue(abs.getDescription().equals("desc"));
	}
	
	@Test
	public void testDtoToEntiteValidSansEtudiant_shouldReturnEntite() {
		AbsenceDto dto = new AbsenceDto("2020-02-20", "2020-02-20","justif","desc", null);
		String date = "2020-02-20";
		LocalDate localDate = LocalDate.parse(date);

		Absence abs = convert.dtoToEntite(dto);

		assertNotNull(abs);
		assertEquals(localDate, abs.getDebut());
		assertTrue(abs.getFin().equals(localDate));
		assertNull(abs.getEtudiant());
		assertTrue(abs.getJustification().equals("justif"));
		assertTrue(abs.getDescription().equals("desc"));
	}
	
	@Test
	public void testDtoToEntiteInvalidSansDates_shouldReturnNull() {
		AbsenceDto dto = new AbsenceDto();
		assertNull(convert.dtoToEntite(dto));
	}
	
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));
	}

	
	@Test
	public void testEntiteToDtoValid_shouldReturnEntite() {
		Etudiant etu = new Etudiant();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Absence abs = new Absence(4, lDate, lDate,"justif","desc", etu);

		AbsenceDto dto = convert.entiteToDto(abs);

		assertNotNull(dto);
		assertTrue(dto.getDebut().equals(date));
		assertTrue(dto.getFin().equals(date));
		assertTrue(dto.getEtudiant().equals(etu));
		assertTrue(dto.getJustification().equals("justif"));
		assertTrue(dto.getDescription().equals("desc"));

	}
	
	@Test
	public void testEntiteToDtoValidSansEtudiant_shouldReturnEntite() {
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Absence abs = new Absence(4, lDate, lDate,"justif","desc", null);

		AbsenceDto dto = convert.entiteToDto(abs);

		assertNotNull(dto);
		assertTrue(dto.getDebut().equals(date));
		assertTrue(dto.getFin().equals(date));
		assertNull(dto.getEtudiant());
		assertTrue(dto.getJustification().equals("justif"));
		assertTrue(dto.getDescription().equals("desc"));

	}

	@Test
	public void testEntiteToDtoInvalidSansDates_shouldReturnEntite() {
		Absence abs = new Absence();
		assertNull(convert.entiteToDto(abs));
	}
	
	@Test
	public void testEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.entiteToDto(null));

	}

	
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<AbsenceDto> listeDto = new ArrayList<AbsenceDto>();
		Etudiant etu = new Etudiant();
		AbsenceDto dto = new AbsenceDto("2020-02-20", "2020-02-20", "justif", null, etu);
		AbsenceDto dto2 = new AbsenceDto("2020-02-20", "2020-02-20", "justif", null, etu);
		
		listeDto.add(dto);
		listeDto.add(dto2);
	
		assertThat(convert.listDtoToEntite(listeDto)).hasSize(2).hasOnlyElementsOfType(Absence.class);
	}
	
	@Test
	public void testListDtoToEntiteValidSansEtudiant_shouldReturnEntite() {
		List<AbsenceDto> listeDto = new ArrayList<AbsenceDto>();
		AbsenceDto dto = new AbsenceDto("2020-02-20", "2020-02-20", "justif", null);
		AbsenceDto dto2 = new AbsenceDto("2020-02-20", "2020-02-20", "justif", null);
		
		listeDto.add(dto);
		listeDto.add(dto2);
		
		assertThat((convert.listDtoToEntite(listeDto))).hasSize(2).hasOnlyElementsOfType(Absence.class);
	}

	
	@Test
	public void testListDtoToEntiteInvalidSansDates_shouldReturnEmpty() {
		List<AbsenceDto> listeDto = new ArrayList<AbsenceDto>();
		AbsenceDto dto = new AbsenceDto();
		AbsenceDto dto2 = new AbsenceDto();
		
		listeDto.add(dto);
		listeDto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listeDto)).isEmpty());
	}

	
	@Test
	public void testListDtoToEntiteNull_shouldReturnEmpty() {
		assertTrue((convert.listDtoToEntite(null)).isEmpty());

	}

	
	@Test
	public void testListEntiteToDtoValid_shouldReturnDto() {
		List<Absence> liste = new ArrayList<Absence>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Etudiant etu = new Etudiant();
		Absence et = new Absence(lDate, lDate,"justif","desc", etu);
		Absence et2 = new Absence(lDate, lDate,"justif","desc", etu);

		liste.add(et);
		liste.add(et2);
		
		List<AbsenceDto> listeReturned = convert.listEntiteToDto(liste);
		
		assertThat(listeReturned).hasSize(2).hasOnlyElementsOfType(AbsenceDto.class);

	}
	
	@Test
	public void testListEntiteToDtoValidSansEtudiant_shouldReturnDto() {
		List<Absence> liste = new ArrayList<Absence>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Absence et = new Absence(lDate, lDate,"justif","desc");
		Absence et2 = new Absence(lDate, lDate,"justif","desc");

		liste.add(et);
		liste.add(et2);
		
		List<AbsenceDto> listeReturned = convert.listEntiteToDto(liste);
		
		assertThat(listeReturned).hasSize(2).hasOnlyElementsOfType(AbsenceDto.class);

	}
	
	@Test
	public void testListEntiteToDtoSansDatesInvalid_shouldReturnEmpty() {
		List<Absence> liste = new ArrayList<Absence>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Absence et = new Absence();
		Absence et2 = new Absence();

		liste.add(et);
		liste.add(et2);
		
		assertTrue(convert.listEntiteToDto(liste).isEmpty());

	}

	
	@Test
	public void testListEntiteToDtoNull_shouldReturnEmpty() {
		assertTrue(convert.listEntiteToDto(null).isEmpty());

	}

}
