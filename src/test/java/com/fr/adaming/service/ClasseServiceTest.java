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
import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class ClasseServiceTest {

	@Autowired
	private IClasseService service;

	// Tests create
	// valide !
	@Test
	@DisplayName("Création d'une classe null")
	public void testCreatingClasseNull_shouldReturnNull() {
		Classe cla = null;
		assertNull(service.create(cla));
	}

	// valide !
	@Test
	@DisplayName("Création d'une classe avec param null")
	public void testCreatingClasseWithNullName_shouldReturnClasse() {
		Classe cla = new Classe(0, null, null);
		assertThat(service.create(cla)).isEqualTo(cla);
	}

	// ça marche pas mais c'est valid (adresse memoire
	@Sql(statements = "INSERT INTO Etudiant (nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES ('Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Création d'une classe correct")
	public void testCreatingCorrectClasse_shouldReturnClasse() {
		List<Etudiant> list = new ArrayList<Etudiant>();
		Etudiant Bob = new Etudiant(1, "Bob", "Marley", "3eme nuage a gauche", "paradis", "jamin@with.you", 0, 0, 0,
				true, true);
		list.add(Bob);
		Classe cla = new Classe(1, "Session2020", list);
		assertThat(service.create(cla)).isEqualTo(cla);
	}

	// Test findAll
	// Valide !
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnNull() {
		assertThat(service.findAll()).isNull();
	}

	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (2, 'Terminal2b', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 classes")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche d'une classe par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}

	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (1, 'Session2020', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche d'une classe par id")
	public void testFindById_shouldReturnEtudiant() {
		Classe cla = new Classe(0, "Session2020", null);
		assertThat(service.findById(1)).isEqualTo(cla);
	}

	// Test update
	@Test
	@DisplayName("Update d'une classe null")
	public void testUpdateNullClasse_shouldReturnFalse() {
		Classe cla = null;
		assertThat(service.update(cla)).isFalse();
	}

	@Test
	@DisplayName("Update d'une classe inexistant dans la bd")
	public void testUpdateInexistantClasse_shouldReturnFalse() {
		Classe cla = new Classe(0, "Session2020", null);
		assertThat(service.update(cla)).isFalse();
	}

	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (1, 'Session2020', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un niveau enregistrer dans la BD")
	public void testUpdateNiveauWithId_shouldReturnTrue() {
		Classe cla = new Classe(0, "Terminal2b", null);
		assertThat(service.update(cla)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (1, 'Session2020', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (1, 'Session2020', null)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}

}
