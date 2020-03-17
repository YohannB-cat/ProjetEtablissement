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

import com.fr.adaming.entity.Matiere;
import com.fr.adaming.entity.Module;

@SpringBootTest
public class MatiereServiceTest {
	
	@Autowired
	private IMatiereService service;

	// Tests create
	@Test
	@DisplayName("Création d'une Matiere null")
	public void testCreatingMatiereNull_shouldReturnNull() {
		Matiere m = null;
		assertNull(service.create(m));
	}

	@Test
	@DisplayName("Création d'une Matiere avec param null")
	@Sql(statements = "DELETE FROM Matiere WHERE nom =null",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithNullName_shouldReturnMatiere() {
		Matiere m = new Matiere(null);
		assertThat(service.create(m)).isEqualTo(m);
	}

	@Test
	@DisplayName("Création d'une Matiere avec correct param")
	@Sql(statements = "DELETE FROM Matiere WHERE nom ='matiere'",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingCorrectMatiere_shouldReturnMatiere() {
		Matiere m = new Matiere("IT");
		assertThat(service.create(m)).isEqualTo(m);
	}

	// Test findAll
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetListMatiere_shouldReturnNull() {
		assertThat(service.findAll()).isNull();
	}

	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (1, 'IT')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (2, 'Biologie')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetListMatiere_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche de Matiere par id non existant")
	public void testFindByIdWithInexistantMatiereId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (1, 'IT')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de Matiere par id")
	public void testFindByIdMatiereWithCorrectParam_shouldReturnMatiere() {
		Matiere m = new Matiere(1, "IT");
		assertThat(service.findById(1)).isEqualTo(m);
	}

	// Test update
	@Test
	@DisplayName("Update d'une Matiere null")
	public void testUpdateNullMatiere_shouldReturnFalse() {
		Matiere m = null;
		assertThat(service.update(m)).isFalse();
	}

	@Test
	@DisplayName("Update d'une Matiere inexistant dans la bd")
	public void testUpdateInexistantMatiere_shouldReturnFalse() {
		Matiere m = new Matiere(1, "IT");
		assertThat(service.update(m)).isFalse();
	}

	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (1, 'IT')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'une Matiere enregistrer dans la BD")
	public void testUpdateMatiereWithId_shouldReturnTrue() {
		Matiere m = new Matiere(1, "Biologie");
		assertThat(service.update(m)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (1, 'IT')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithMatiereIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Matiere (id, nom) VALUES (1, 'IT')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidIdMAtiere_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}

}
