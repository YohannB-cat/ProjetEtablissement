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
public class ModuleServiceTest {

	@Autowired
	private IModuleService service;

	// Tests create
	@Test
	@DisplayName("Création d'un Module null")
	public void testCreatingModuleNull_shouldReturnNull() {
		Module m = null;
		assertNull(service.create(m));
	}

	@Test
	@DisplayName("Création d'un Module avec param null")
	public void testCreatingModuleWithNullName_shouldReturnNiveau() {
		Module m = new Module(0, null, null);
		assertThat(service.create(m)).isEqualTo(m);
	}

	@Test
	@DisplayName("Création d'un Module avec correct")
	public void testCreatingCorrectModule_shouldReturnNiveau() {
		Matiere mat = new Matiere();
		List<Matiere> list = new ArrayList<Matiere>();
		list.add(mat);
		Module m = new Module(1, "JAVA", list);
		assertThat(service.create(m)).isEqualTo(m);
	}

	// Test findAll
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	@Sql(statements = "INSERT INTO Module (id, nom, matiere) VALUES (1, 'JAVA', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module (id, nom, matiere) VALUES (2, 'Spring', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche de Module par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Module (id, nom, matiere) VALUES (1, 'JAVA', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de Module par id")
	public void testFindById_shouldReturnNiveau() {
		Module m = new Module(1, "JAVA", null);
		assertThat(service.findById(1)).isEqualTo(m);
	}

	// Test update
	@Test
	@DisplayName("Update d'un Module null")
	public void testUpdateNullModule_shouldReturnFalse() {
		Module m = null;
		assertThat(service.update(m)).isFalse();
	}

	@Test
	@DisplayName("Update d'un Module inexistant dans la bd")
	public void testUpdateInexistantModule_shouldReturnFalse() {
		Matiere mat = new Matiere();
		List<Matiere> list = new ArrayList<Matiere>();
		list.add(mat);
		Module m = new Module(1, "JAVA", list);
		assertThat(service.update(m)).isFalse();
	}

	@Sql(statements = "INSERT INTO Module (id, nom, matiere) VALUES (1, 'JAVA', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un Module enregistrer dans la BD")
	public void testUpdateModuleWithId_shouldReturnTrue() {
		Matiere mat = new Matiere();
		List<Matiere> list = new ArrayList<Matiere>();
		list.add(mat);
		Module m = new Module(1, "JPA", list);
		assertThat(service.update(m)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Module (id, nom, matiere) VALUES (1, 'JAVA', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Module (id, nom, matiere) VALUES (1, 'JAVA', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}
}
