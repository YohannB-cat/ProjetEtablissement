package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class AbsenceServiceTest {

	@Autowired
	private IAbsenceService service;

	// Tests create
	@Test
	@DisplayName("Création d'une Absence null")
	public void testCreatingAbsenceNull_shouldReturnNull() {
		Absence abs = null;
		assertNull(service.create(abs));
	}

	@Test
	@DisplayName("Création d'une Absence avec param null")
	public void testCreatingAbsenceWithNullName_shouldReturnAbsence() {
		Absence abs = new Absence(0, null, null, null, null, null);
		assertThat(service.create(abs)).isEqualTo(abs);
	}

	@Test
	@DisplayName("Création d'une Absence avec correct")
	public void testCreatingCorrectAbsence_shouldReturnAbsence() {
		Etudiant etu = new Etudiant();
		Absence abs = new Absence(0, null, null, "J'aime pas les bananes", "On lui à demander de manger des bananes",
				etu);
		assertThat(service.create(abs)).isEqualTo(abs);
	}

	// Test findAll
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin, justification, description, etudiant) VALUES (1, null, null,'J\'aime pas les bananes', 'On lui à demander de manger des bananes', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Absence (id, debut, fin, justification, description, etudiant) VALUES (2, null, null,'Je suis généreux en bananes', 'Je voulais donner des bananes à un collegue', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche d'Absence par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin, justification, description, etudiant) VALUES (1, null, null,'J\'aime pas les bananes', 'On lui à demander de manger des bananes', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche d'Absence par id")
	public void testFindById_shouldReturnEtudiant() {
		Etudiant etu = new Etudiant();
		Absence abs = new Absence(0, null, null, "J'aime pas les bananes", "On lui à demander de manger des bananes",
				etu);
		assertThat(service.findById(1)).isEqualTo(abs);
	}

	// Test update
	@Test
	@DisplayName("Update d'une Absence null")
	public void testUpdateNullAbsence_shouldReturnFalse() {
		Absence abs = null;
		assertThat(service.update(abs)).isFalse();
	}

	@Test
	@DisplayName("Update d'une Absence inexistant dans la bd")
	public void testUpdateInexistantAbsence_shouldReturnFalse() {
		Etudiant etu = new Etudiant();
		Absence abs = new Absence(0, null, null, "J'aime pas les bananes", "On lui à demander de manger des bananes",
				etu);
		assertThat(service.update(abs)).isFalse();
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin, justification, description, etudiant) VALUES (1, null, null,'J\'aime pas les bananes', 'On lui à demander de manger des bananes', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'une Absence enregistrer dans la BD")
	public void testUpdateAbsenceWithId_shouldReturnTrue() {
		Etudiant etu = new Etudiant();
		Absence abs = new Absence(0, null, null, "J'aime les bananes", "Il y avait une promo de bananes", etu);
		assertThat(service.update(abs)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Absence (id, debut, fin, justification, description, etudiant) VALUES (1, null, null,'J\'aime pas les bananes', 'On lui à demander de manger des bananes', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin, justification, description, etudiant) VALUES (1, null, null,'J\'aime pas les bananes', 'On lui à demander de manger des bananes', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}

}
