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
	@Autowired
	private IMatiereService matService;

	// Tests create
	@Test
	@DisplayName("Création d'un Module null")
	public void testCreatingModuleNull_shouldReturnNull() {
		Module m = null;
		assertNull(service.create(m));
	}

	@Test
	@DisplayName("Création d'un Module avec param null")
	@Sql(statements = "DELETE FROM Module WHERE nom ='null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingModuleWithNullName_shouldReturnModule() {
		Module m = new Module(1, null);
		Module modulCreate = service.create(m);
		assertThat(modulCreate).hasFieldOrPropertyWithValue("nom", m.getNom());
//		assertThat(modulCreate).hasFieldOrPropertyWithValue("id", m.getId());
	}

	@Test
	@DisplayName("Création d'un Module avec correct")
	@Sql(statements = "DELETE FROM Module WHERE nom='null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE nom ='Matiere4Test'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE nom ='JAVA4Test'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingCorrectModule_shouldReturnModule() {
		Matiere mat = new Matiere(1,"Matiere4Test");

		List<Matiere> list = new ArrayList<>();
		list.add(mat);
		matService.create(mat);
		Module m = new Module(1, "JAVA4Test", list);
		Module mcreate = service.create(m);
		
//		assertThat(mcreate).hasFieldOrPropertyWithValue("id", m.getId());
		assertThat(mcreate).hasFieldOrPropertyWithValue("nom", m.getNom());
		assertThat(mcreate.getMatieres()).asList().hasSize(1);
		assertThat(mcreate.getMatieres().get(0)).hasFieldOrPropertyWithValue("nom", "Matiere4Test");
	}

	// Test findAll
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnNull() {
		assertThat(service.findAll()).isEmpty();
	}

	@Sql (statements = "DELETE FROM Module WHERE nom = 'null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module  VALUES (null, 'JAVA')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module VALUES (null, 'Spring')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE nom = 'JAVA'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE nom = 'SPRING'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	
	
	@Sql(statements = "DELETE FROM Module WHERE nom='null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module VALUES (1, 'JAVA')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE nom = 'JAVA'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche de Module par id")
	public void testFindById_shouldReturnModule() {
		Module m = new Module(1, "JAVA");
		Module mFind = service.findById(m.getId());
		assertThat(mFind).hasFieldOrPropertyWithValue("id", m.getId());
		assertThat(mFind).hasFieldOrPropertyWithValue("nom", m.getNom());
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
	
	
	//PROBLEMMMMMMEE false/true
	// pb colum doesn'tcount match value at row 1
	@Sql(statements = "DELETE FROM Module WHERE nom='null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module (id,nom)  VALUES (1,'it4TEST')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE nom = 'JAVA4TEST'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un Module enregistrer dans la BD")
	public void testUpdateModuleWithId_shouldReturnTrue() {
		Matiere mat = new Matiere(1,"JAVA4TEST");
		List<Matiere> list = new ArrayList<>();
		list.add(mat);
		Module m = new Module("JPA", list);
		assertThat(service.update(m)).isTrue();
	}

	// Test deleteById
	@Sql (statements = "DELETE FROM Module WHERE nom = 'null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module VALUES (1, 'JAVA')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql (statements = "DELETE FROM Module WHERE nom = 'null'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module VALUES (1, 'JAVA')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}
}
