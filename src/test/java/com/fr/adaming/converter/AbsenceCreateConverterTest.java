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
import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class AbsenceCreateConverterTest {

	@Autowired
	public IConverter<Absence, AbsenceDtoCreate> convert;

	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {		
		Etudiant etu = new Etudiant();
		AbsenceDtoCreate dto = new AbsenceDtoCreate(4, "2020-02-20", "2020-02-20","justif","desc", etu);
		String date = "2020-02-20";
		LocalDate localDate = LocalDate.parse(date);

		Absence abs = convert.dtoToEntite(dto);

		assertNotNull(abs);
		assertEquals(4, abs.getId());
		assertEquals(localDate, abs.getDebut());
		assertTrue(abs.getFin().equals(localDate));
		assertTrue(abs.getEtudiant().equals(etu));
		assertTrue(abs.getJustification().equals("justif"));
		assertTrue(abs.getDescription().equals("desc"));
	}
	
	@Test
	public void testDtoToEntiteValidSansEtudiant_shouldReturnEntite() {		
		AbsenceDtoCreate dto = new AbsenceDtoCreate(5, "2020-02-20", "2020-02-20","justif","desc");
		String date = "2020-02-20";
		LocalDate localDate = LocalDate.parse(date);

		Absence abs = convert.dtoToEntite(dto);

		assertNotNull(abs);
		assertEquals(5, abs.getId());
		assertEquals(localDate, abs.getDebut());
		assertTrue(abs.getFin().equals(localDate));
		assertNull(abs.getEtudiant());
		assertTrue(abs.getJustification().equals("justif"));
		assertTrue(abs.getDescription().equals("desc"));
	}

	@Test
	public void testDtoToEntiteInvalidSansDates_shouldReturnNull() {
		AbsenceDtoCreate dto = new AbsenceDtoCreate();
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

		AbsenceDtoCreate dto = convert.entiteToDto(abs);

		assertNotNull(dto);
		assertTrue(abs.getId() == 4);
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

		AbsenceDtoCreate dto = convert.entiteToDto(abs);

		assertNotNull(dto);
		assertTrue(abs.getId() == 4);
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
		List<AbsenceDtoCreate> listeDto = new ArrayList<AbsenceDtoCreate>();
		Etudiant etu = new Etudiant();
		AbsenceDtoCreate dto = new AbsenceDtoCreate(10, "2020-02-20", "2020-02-20", null, null, etu);
		AbsenceDtoCreate dto2 = new AbsenceDtoCreate(12, "2020-02-20", "2020-02-20", "justif", null, etu);
		
		listeDto.add(dto);
		listeDto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listeDto).size()==2));
	}
	
	@Test
	public void testListDtoToEntiteValidSansEtudiant_shouldReturnEntite() {
		List<AbsenceDtoCreate> listeDto = new ArrayList<AbsenceDtoCreate>();
		AbsenceDtoCreate dto = new AbsenceDtoCreate(10, "2020-02-20", "2020-02-20", null, null, null);
		AbsenceDtoCreate dto2 = new AbsenceDtoCreate(12, "2020-02-20", "2020-02-20", "justif", null, null);
		
		listeDto.add(dto);
		listeDto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listeDto).size()==2));
	}
	
	@Test
	public void testListDtoToEntiteInvalidSansDates_shouldReturnEmpty() {
		List<AbsenceDtoCreate> listeDto = new ArrayList<AbsenceDtoCreate>();
		AbsenceDtoCreate dto = new AbsenceDtoCreate(10, null, null, null, null, null);
		AbsenceDtoCreate dto2 = new AbsenceDtoCreate();
		
		listeDto.add(dto);
		listeDto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listeDto)).isEmpty());
	}

	@Test
	public void testListDtoToEntiteNull_shouldReturnEmpty() {
		assertTrue((convert.listDtoToEntite(null)).isEmpty());

	}

	@Test
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Absence> liste = new ArrayList<Absence>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Etudiant etu = new Etudiant();
		Absence et = new Absence(4, lDate, lDate,"justif","desc", etu);
		Absence et2 = new Absence(5, lDate, lDate,"justif","desc", etu);

		liste.add(et);
		liste.add(et2);
		
		List<AbsenceDtoCreate> listeReturned = convert.listEntiteToDto(liste);
		
		assertTrue(listeReturned.size() > 1);
	}
	
	@Test
	public void testListEntiteToDtoValidSansEtudiant_shouldReturnEntite() {
		List<Absence> liste = new ArrayList<Absence>();
		String date = "2020-02-20";
		LocalDate lDate = LocalDate.parse(date);
		Absence et = new Absence(4, lDate, lDate,"justif","desc", null);
		Absence et2 = new Absence(5, lDate, lDate,"justif","desc", null);

		liste.add(et);
		liste.add(et2);
		
		List<AbsenceDtoCreate> listeReturned = convert.listEntiteToDto(liste);
		
		assertTrue(listeReturned.size() > 1);
	}

	@Test
	public void testListEntiteToDtoInvalidSansDates_shouldReturnEmpty() {
		List<Absence> liste = new ArrayList<Absence>();
		Absence et = new Absence(4, null, null,"justif","desc", null);
		Absence et2 = new Absence();

		liste.add(et);
		liste.add(et2);
		
		assertTrue(convert.listEntiteToDto(liste).isEmpty());
	}


	@Test
	public void testListEntiteToDtoNull_shouldReturnNull() {
		assertTrue(convert.listEntiteToDto(null).isEmpty());
	}

}
