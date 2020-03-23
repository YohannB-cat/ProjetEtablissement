package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Module;
import com.fr.adaming.entity.Note;

/**
 * Classe test de la couche converter du dto NoteDto
 * @author clara
 * @since 1.0.X
 */
@SpringBootTest
public class NoteConverterTest {

	@Autowired
	public IConverter<Note, NoteDto> convert;

	/**
	 * Vérification conversion de dto à entite ok
	 */
	@Test
	@DisplayName("test 1")
	public void testDtoToEntiteValid_shouldReturnEntite() {
		NoteDto dto = new NoteDto(new Module(),15.3f,new Etudiant(),new Examen());

		Note abs = convert.dtoToEntite(dto);

		assertTrue(abs.getValeur()==15.3f);


	}


	/**
	 * Vérification conversion de dto null retourne une entite null 
	 */
	@Test
	@DisplayName("test 3")
	public void testDtoToEntiteNull_shouldReturnNull() {
		assertNull(convert.dtoToEntite(null));

	}

	/**
	 * Vérification conversion de entite à dto ok
	 */
	@Test
	@DisplayName("test 4")
	public void testEntiteToDtoValid_shouldReturnEntite() {
		Note abs = new Note(5, new Module(),18.4f, new Etudiant(),new Examen());

		NoteDto dto = convert.entiteToDto(abs);

		assertTrue(dto.getValeur()==18.4f);

	}



	/**
	 * Vérification conversion de entite null retourne dto null
	 */
	@Test
	@DisplayName("test 6")
	public void testEntiteToDtoNull_shouldReturnNull() {
		assertNull(convert.entiteToDto(null));

	}

	/**
	 * Vérification conversion listeDto à liste entité ok
	 */
	@Test
	@DisplayName("test 7")
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<NoteDto> listedto = new ArrayList<NoteDto>();
		NoteDto dto = new NoteDto(new Module(),15.3f,new Etudiant(),new Examen());
		
		NoteDto dto2 = new NoteDto(new Module(),15.8f,new Etudiant(),new Examen());
		listedto.add(dto);
		listedto.add(dto2);
		
		assertTrue((convert.listDtoToEntite(listedto).size()==2));

	}


	/**
	 * Vérification conversion listeDto null retourne liste entite vide 
	 */
	@Test
	@DisplayName("test 9")
	public void testListDtoToEntiteNull_shouldReturnNull() {
		assertTrue(convert.listDtoToEntite(null).isEmpty());
	}

	/**
	 * Vérification conversion listeEntite à listeDto ok
	 */
	@Test
	@DisplayName("test 10")
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Note> liste = new ArrayList<Note>();
		Note et = new Note(5, new Module(),18.4f, new Etudiant(),new Examen());
		Note et2 = new Note(18, new Module(),18.48f, new Etudiant(),new Examen());

		liste.add(et);
		liste.add(et2);
		
		assertTrue((convert.listEntiteToDto(liste).size()==2));

	}

	/**
	 * Vérification conversion listeEntite null retourne listeDto vide
	 */
	@Test
	@DisplayName("test 12")
	public void testListEntiteToDtoNull_shouldReturnNull() {
		assertThat(convert.listEntiteToDto(null)).isNotNull().isEmpty();
	}

}
