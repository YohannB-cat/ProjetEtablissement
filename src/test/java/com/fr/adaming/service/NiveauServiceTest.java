package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

@SpringBootTest
public class NiveauServiceTest {

	@Autowired
	private INiveauService service;

	// Tests create
	@Test
	@DisplayName("Création d'un niveau null")
	public void testCreatingNiveauNull_shouldReturnNull() {
		Niveau niv = null;
		assertNull(service.create(niv));
	}

	@Test
	@DisplayName("Création d'un niveau avec param null")
	public void testCreatingNiveauWithNullName_shouldReturnEtudiant() {
		Niveau niv = new Niveau(null, 0, null);
		assertThat(service.create(niv)).isEqualTo(niv);
	}

	@Test
	@DisplayName("Création d'un niveau avec correct")
	public void testCreatingCorrectNiveau_shouldReturnEtudiant() {
		Classe term1 = new Classe();
		Classe term2 = new Classe();
		List<Classe> list = new ArrayList<Classe>();
		list.add(term1);
		list.add(term2);
		Niveau niv = new Niveau(list, 0, "Bof");
		assertThat(service.create(niv)).isEqualTo(niv);
	}

	// Test findAll
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	@Sql(statements = "INSERT INTO Niveau (classes, id, nom) VALUES (null, 1, 'Maternel')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Niveau (classes, id, nom) VALUES (null, 2, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM chat WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM chat WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 chats")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche de niveau par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Niveau (classes, id, nom) VALUES (null, 1, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM chat WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de niveau par id")
	public void testFindById_shouldReturnEtudiant() {
		Classe term1 = new Classe();
		Classe term2 = new Classe();
		List<Classe> list = new ArrayList<Classe>();
		list.add(term1);
		list.add(term2);
		Niveau niv = new Niveau(list, 0, "Bof");
		assertThat(service.findById(1)).isEqualTo(niv);
	}

	// Test update
	@Test
	@DisplayName("Update d'un niveau null")
	public void testUpdateNullNiveau_shouldReturnFalse() {
		Niveau niv = null;
		assertThat(service.update(niv)).isFalse();
	}

	@Test
	@DisplayName("Update d'un niveau inexistant dans la bd")
	public void testUpdateInexistantNiveau_shouldReturnFalse() {
		Classe term1 = new Classe();
		Classe term2 = new Classe();
		List<Classe> list = new ArrayList<Classe>();
		list.add(term1);
		list.add(term2);
		Niveau niv = new Niveau(list, 0, "Bof");
		assertThat(service.update(niv)).isFalse();
	}

	@Sql(statements = "INSERT INTO Niveau (classes, id, nom) VALUES (null, 1, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM chat WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un niveau enregistrer dans la BD")
	public void testUpdateNiveauWithId_shouldReturnTrue() {
		Classe term1 = new Classe();
		Classe term2 = new Classe();
		List<Classe> list = new ArrayList<Classe>();
		list.add(term1);
		list.add(term2);
		Niveau niv = new Niveau(list, 0, "Bof");
		assertThat(service.update(niv)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Niveau (classes, id, nom) VALUES (null, 1, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM chat WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Niveau (classes, id, nom) VALUES (null, 1, 'Primaire')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM chat WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}
}
