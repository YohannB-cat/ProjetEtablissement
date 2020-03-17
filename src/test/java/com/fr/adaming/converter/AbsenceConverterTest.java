package com.fr.adaming.converter;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.entity.Absence;

@SpringBootTest
public class AbsenceConverterTest implements IConverterTest {

	@Autowired
	public IConverter<Absence, AbsenceDto> convert;

	@Override
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		AbsenceDto dto = new AbsenceDto(null,null,"justif","desc",null);

		Absence abs = convert.dtoToEntite(dto);

		assertTrue(abs.getId()!=0);
		assertTrue(abs.getDebut().equals(null));
		assertTrue(abs.getFin().equals(null));
		assertTrue(abs.getEtudiant().equals(null));
		assertTrue(abs.getJustification().equals("justif"));
		assertTrue(abs.getDescription().equals("desc"));

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
		Absence abs = new Absence(4,null,null,"justif","desc",null);

		AbsenceDto dto = convert.entiteToDto(abs);

		assertTrue(dto.getDebut().equals(null));
		assertTrue(dto.getFin().equals(null));
		assertTrue(dto.getEtudiant().equals(null));
		assertTrue(dto.getJustification().equals("justif"));
		assertTrue(dto.getDescription().equals("desc"));

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
		List<AbsenceDto> listedto = new ArrayList<AbsenceDto>();
		AbsenceDto dto = new AbsenceDto(null,null,"justif","desc",null);
		
		AbsenceDto dto2 = new AbsenceDto(null,null,"justif2","desc2",null);
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
		List<Absence> liste = new ArrayList<Absence>();
		Absence et = new Absence(4,null,null,"justif","desc",null);
		Absence et2 = new Absence(4,null,null,"justif","desc",null);

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
