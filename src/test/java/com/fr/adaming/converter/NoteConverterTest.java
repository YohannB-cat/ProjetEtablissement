package com.fr.adaming.converter;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Module;
import com.fr.adaming.entity.Note;

@SpringBootTest
public class NoteConverterTest implements IConverterTest {

	@Autowired
	public IConverter<Note, NoteDto> convert;

	@Override
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		NoteDto dto = new NoteDto(new Module(),15.3f,new Etudiant(),new Examen());

		Note abs = convert.dtoToEntite(dto);

		assertTrue(abs.getId()!=0);
		assertTrue(abs.getValeur()==15.3f);


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
		Note abs = new Note(5, new Module(),18.4f, new Etudiant(),new Examen());

		NoteDto dto = convert.entiteToDto(abs);

		assertTrue(dto.getValeur()==18.4f);

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
		List<NoteDto> listedto = new ArrayList<NoteDto>();
		NoteDto dto = new NoteDto(new Module(),15.3f,new Etudiant(),new Examen());
		
		NoteDto dto2 = new NoteDto(new Module(),15.8f,new Etudiant(),new Examen());
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
		List<Note> liste = new ArrayList<Note>();
		Note et = new Note(5, new Module(),18.4f, new Etudiant(),new Examen());
		Note et2 = new Note(18, new Module(),18.48f, new Etudiant(),new Examen());

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
