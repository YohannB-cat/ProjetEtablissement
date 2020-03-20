package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Module;
import com.fr.adaming.entity.Note;

@SpringBootTest
public class NoteServiceTest {

	@Autowired
	private INoteService service;

	// Tests create
	@Test
	@DisplayName("Création d'une Note null")
	public void testCreatingNoteNull_shouldReturnNull() {
		Note n = null;
		assertNull(service.create(n));
	}

	@Test
	@DisplayName("Création d'une Note avec param null")
	public void testCreatingNoteWithNullName_shouldReturnNiveau() {
		Note n = new Note(0, null, 0f, null, null);
		assertThat(service.create(n)).isEqualTo(n);
	}

	@Test
	@DisplayName("Création d'une Note correcte")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingCorrectNote_shouldReturnNiveau() {
		Note n = new Note(0, null, 12f, null, null);
		Note cn = service.create(n);
		assertEquals(cn.getEtudiant(),n.getEtudiant());
		assertTrue(cn.getValeur()==n.getValeur());
		assertEquals(cn.getExamen(),n.getExamen());
		assertEquals(cn.getModule(),n.getModule());
	}
	@Test
	@DisplayName("Création d'une Note avec un etudiant inexistant")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingNoteWithoutExistingEtudiant_shouldReturnNiveau() {
		Etudiant et = new Etudiant();
		Note n = new Note(0, null, 12f, et, null);
		assertThat(service.create(n)).isNull();
	}
	@Test
	@DisplayName("Création d'une Note avec un examen inexistant")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingNoteWithoutExistingExamen_shouldReturnNiveau() {
		Examen ex = new Examen();
		Note n = new Note(0, null, 12f, null, ex);
		assertThat(service.create(n)).isNull();
	}
	@Test
	@DisplayName("Création d'une Note avec un module inexistant")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingNoteWithoutExistingModule_shouldReturnNiveau() {
		Module mo = new Module();
		Note n = new Note(0, mo, 12f, null, null);
		assertThat(service.create(n)).isNull();
	}
	
	
	// Test findAll
	@Test
	@DisplayName("Demande de la liste vide")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (1, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (2, null, 14, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche de Note par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (1, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de Note par id")
	public void testFindById_shouldReturnNiveau() {
		assertEquals(service.findById(1).getEtudiant(),null);
		assertEquals(service.findById(1).getModule(),null);
		assertEquals(service.findById(1).getExamen(),null);
		assertEquals(service.findById(1).getValeur(),12);
	}

	// Test update
	@Test
	@DisplayName("Update d'une Note null")
	public void testUpdateNullNote_shouldReturnFalse() {
		Note n = null;
		assertThat(service.update(n)).isFalse();
	}

	@Test
	@DisplayName("Update d'une Note inexistant dans la bd")
	public void testUpdateInexistantNote_shouldReturnFalse() {
		Module m = new Module();
		Etudiant et = new Etudiant();
		Examen ex = new Examen();
		Note n = new Note(0, m, 12f, et, ex);
		assertThat(service.update(n)).isFalse();
	}

	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (5, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'une Note enregistrer dans la BD")
	public void testUpdateNoteWithId_shouldReturnTrue() {
		Note n = new Note(5, null, 14f, null, null);
		assertTrue(service.update(n));
	}

	// Test deleteById
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (1, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}
	
	

}
