package com.fr.adaming.converter;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Module;
import com.fr.adaming.entity.Note;

@SpringBootTest
public class NoteCreateConverterTest {

	@Autowired
	public IConverter<Note, NoteDtoCreate> convert;

	@Test
	@DisplayName("test 1")
	public void testDtoToEntiteValid_shouldReturnEntite() {
		NoteDtoCreate dto = new NoteDtoCreate(3, new Module(),15.3f,new Etudiant(),new Examen());

		Note abs = convert.dtoToEntite(dto);

		assertTrue(abs.getId()==3);
		assertTrue(abs.getValeur()==15.3f);


	}

	@Test
	@DisplayName("test 2")
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));

	}

	@Test
	@DisplayName("test 3")
	public void testEntiteToDtoValid_shouldReturnEntite() {
		Note abs = new Note(5, new Module(),18.4f, new Etudiant(),new Examen());

		NoteDtoCreate dto = convert.entiteToDto(abs);

		assertTrue(dto.getValeur()==18.4f);
		assertTrue(dto.getId()==5);

	}


	@Test
	@DisplayName("test 4")
	public void testEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.entiteToDto(null));

	}

	@Test
	@DisplayName("test 5")
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<NoteDtoCreate> listedto = new ArrayList<NoteDtoCreate>();
		NoteDtoCreate dto = new NoteDtoCreate(4,new Module(),15.3f,new Etudiant(),new Examen());
		
		NoteDtoCreate dto2 = new NoteDtoCreate(18, new Module(),15.8f,new Etudiant(),new Examen());
		listedto.add(dto);
		listedto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listedto).size()==2));

	}

	@Test
	@DisplayName("test 6")
	public void testListDtoToEntiteNull_shouldReturnNull() {
		assertTrue(convert.listDtoToEntite(null).isEmpty());

	}

	@Test
	@DisplayName("test 7")
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Note> liste = new ArrayList<Note>();
		Note et = new Note(5, new Module(),18.4f, new Etudiant(),new Examen());
		Note et2 = new Note(18, new Module(),18.48f, new Etudiant(),new Examen());

		liste.add(et);
		liste.add(et2);
		
		assertTrue((convert.listEntiteToDto(liste).size()==2));

	}

	@Test
	@DisplayName("test 8")
	public void testListEntiteToDtoNull_shouldReturnNull() {
		assertTrue(convert.listEntiteToDto(null).isEmpty());

	}


}
