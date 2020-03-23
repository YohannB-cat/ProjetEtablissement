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

/**
 * Classe test de la couche service de l'entité Note
 * @author clara
 * @since 1.0.X
 */
@SpringBootTest
public class NoteServiceTest {

	@Autowired
	private INoteService service;

	// Tests create
	/**
	 * Vérification que la création d'une note null renvoie null 
	 */
	@Test
	@DisplayName("Création d'une Note null")
	public void testCreatingNoteNull_shouldReturnNull() {
		Note n = null;
		assertNull(service.create(n));
	}

	/**
	 * Vérifiation que la création d'une note (attribut valides, mais null) fonctionne
	 */
	@Test
	@DisplayName("Création d'une Note avec param null")
	public void testCreatingNoteWithNullName_shouldReturnNote() {
		Note n = new Note(0, null, 0f, null, null);
		assertThat(service.create(n)).isEqualTo(n);
	}

	/**
	 * Vérifiation que la création d'une note (attribut valides, mais null) fonctionne
	 */
	@Test
	@DisplayName("Création d'une Note correcte")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingCorrectNote_shouldReturnNote() {
		Note n = new Note(0, null, 12f, null, null);
		Note cn = service.create(n);
		assertEquals(cn.getEtudiant(),n.getEtudiant());
		assertTrue(cn.getValeur()==n.getValeur());
		assertEquals(cn.getExamen(),n.getExamen());
		assertEquals(cn.getModule(),n.getModule());
	}
	/**
	 * Vérification que la création d'une note avec un étudiant inexistant dans la base de données, ne fonctionne pas et retourne null
	 */
	@Test
	@DisplayName("Création d'une Note avec un etudiant inexistant")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingNoteWithoutExistingEtudiant_shouldReturnNote() {
		Etudiant et = new Etudiant();
		Note n = new Note(0, null, 12f, et, null);
		assertThat(service.create(n)).isNull();
	}
	/**
	 * Vérification que la création d'une note avec un examen inexistant dans la base de données, ne fonctionne pas et retourne null
	 */
	@Test
	@DisplayName("Création d'une Note avec un examen inexistant")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingNoteWithoutExistingExamen_shouldReturnNote() {
		Examen ex = new Examen();
		Note n = new Note(0, null, 12f, null, ex);
		assertThat(service.create(n)).isNull();
	}
	/**
	 * Vérification que la création d'une note avec un module inexistant dans la base de données, ne fonctionne pas et retourne null
	 */
	@Test
	@DisplayName("Création d'une Note avec un module inexistant")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreatingNoteWithoutExistingModule_shouldReturnNote() {
		Module mo = new Module();
		Note n = new Note(0, mo, 12f, null, null);
		assertThat(service.create(n)).isNull();
	}
	
	
	// Test findAll
	/**
	 * Vérification que findAll retourne une liste vide quand il n'y en a pas dans la base de données
	 */
	@Test
	@DisplayName("Demande de la liste vide")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	/**
	 * Vérification que findAll retourne une liste de celles présentes dans la base de données
	 */
	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (1, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (2, null, 14, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	/**
	 * Vérification que le retour de findById avec id inexistant soit null
	 */
	@Test
	@DisplayName("Recherche de Note par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	/**
	 * Vérification que le retour de findById soit le bon objet
	 */
	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (1, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de Note par id")
	public void testFindById_shouldReturnNote() {
		assertEquals(service.findById(1).getEtudiant(),null);
		assertEquals(service.findById(1).getModule(),null);
		assertEquals(service.findById(1).getExamen(),null);
		assertEquals(service.findById(1).getValeur(),12);
	}

	// Test update
	/**
	 * Vérification que l'update d'une note null ne fonctionne pas
	 */
	@Test
	@DisplayName("Update d'une Note null")
	public void testUpdateNullNote_shouldReturnFalse() {
		Note n = null;
		assertThat(service.update(n)).isFalse();
	}

	/**
	 * Vérification que l'update d'une note n'existant pas dans la base de donnée ne fonctionne pas
	 */
	@Test
	@DisplayName("Update d'une Note inexistant dans la bd")
	public void testUpdateInexistantNote_shouldReturnFalse() {
		Module m = new Module();
		Etudiant et = new Etudiant();
		Examen ex = new Examen();
		Note n = new Note(0, m, 12f, et, ex);
		assertThat(service.update(n)).isFalse();
	}

	/**
	 * Vérification que l'update fonctionne
	 */
	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (5, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'une Note enregistrer dans la BD")
	public void testUpdateNoteWithId_shouldReturnTrue() {
		Note n = new Note(5, null, 14f, null, null);
		assertTrue(service.update(n));
	}

	// Test deleteById
	/**
	 * Vérification que la suppression avec id invalide ne fonctionne pas
	 */
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	/**
	 * Vérification que la suppression avec id valide fontionne 
	 */
	@Sql(statements = "INSERT INTO Note (id, module_id, valeur, etudiant_id, examen_id) VALUES (1, null, 12, null, null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}
	
	

}
