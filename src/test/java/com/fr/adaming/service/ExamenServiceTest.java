package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Examen;

@SpringBootTest
public class ExamenServiceTest {

	@Autowired
	private IExamenService service;

	// Tests create
	@Test
	@DisplayName("Création d'un Examen null")
	public void testCreatingExamenNull_shouldReturnNull() {
		Examen exam = null;
		assertNull(service.create(exam));
	}

	@Test
	@DisplayName("Création d'un Examen avec param null")
	public void testCreatingExamenWithNullName_shouldReturnNiveau() {
		Examen exam = new Examen(0, null, null, 0D);
		assertThat(service.create(exam)).isEqualTo(exam);
	}

	@Test
	@DisplayName("Création d'un Examen avec correct")
	public void testCreatingCorrectExamen_shouldReturnNiveau() {
		Examen exam = new Examen(0, null, "DS", 3D);
		assertThat(service.create(exam)).isEqualTo(exam);
	}

	// Test findAll
	@Test
	@Sql(statements = "delete from Examen",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	@Sql(statements = "INSERT INTO Examen (id, date, type, coefficient) VALUES (1, null, 'DS', 3)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Examen (id, date, type, coefficient) VALUES (2, null, 'DM', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche d'un Examen par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Examen (id, date, type, coefficient) VALUES (1, null, 'DS', 3)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche d'un Examen par id")
	public void testFindById_shouldReturnNiveau() {
		Examen exam = new Examen(1, null, "DS", 3D);
		assertThat(service.findById(1)).isEqualTo(exam);
	}

	// Test update
	@Test
	@DisplayName("Update d'un Examen null")
	public void testUpdateNullExamen_shouldReturnFalse() {
		Examen exam = null;
		assertThat(service.update(exam)).isFalse();
	}

	@Test
	@DisplayName("Update d'un Examen inexistant dans la bd")
	public void testUpdateInexistantExamen_shouldReturnFalse() {
		Examen exam = new Examen(1, null, "DS", 3D);
		assertThat(service.update(exam)).isFalse();
	}

	@Sql(statements = "INSERT INTO Examen (id, date, type, coefficient) VALUES (1, null, 'DS', 3)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un Examen enregistrer dans la BD")
	public void testUpdateExamenWithId_shouldReturnTrue() {
		Examen exam = new Examen(1, null, "DM", 2D);
		assertThat(service.update(exam)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Examen (id, date, type, coefficient) VALUES (1, null, 'DS', 3)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Examen (id, date, type, coefficient) VALUES (1, null, 'DS', 3)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}

	@Sql(statements = "INSERT INTO Examen (id, type) VALUES (1, 'exam1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Examen (id, type) VALUES (2, 'exam2')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module (id, nom) VALUES (1, 'module1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere (id, nom,module_id) VALUES (1, 'matiere1',1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id,valeur,examen_id) VALUES (1, 1,12,1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id,valeur,examen_id) VALUES (2, 1,13,2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testListByMatiere_shouldReturnListOfExamen() {
		assertThat(service.listByMatiere(1)).hasSize(2).hasOnlyElementsOfType(Examen.class);
		
	}
	
}
