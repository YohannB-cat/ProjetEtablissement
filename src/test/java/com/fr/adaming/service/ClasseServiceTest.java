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


/**
 * Tests de la classe ClasseService
 * @author Flavien
 * @since 1.0.x
 *
 */
@SpringBootTest
public class ClasseServiceTest {

	@Autowired
	private IClasseService service;

	// Tests create
	// valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode Creat dans le cas ou le service serai null
	 */
	@Test
	@DisplayName("Création d'une classe null")
	public void testCreatingClasseNull_shouldReturnNull() {
		Classe cla = null;
		assertNull(service.create(cla));
	}

	// valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode Creat dans le cas ou le service à des param nulls
	 */
	@Test
	@DisplayName("Création d'une classe avec param null")
	public void testCreatingClasseWithNullName_shouldReturnClasse() {
		Classe cla = new Classe(0, null, null);
		assertThat(service.create(cla)).isEqualTo(cla);
	}

	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode Creat avec un service valide
	 */
	@Sql(statements = "INSERT INTO Etudiant (id,nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Création d'une classe correct")
	public void testCreatingCorrectClasse_shouldReturnClasse() {
		List<Etudiant> list = new ArrayList<Etudiant>();
		Etudiant Bob = new Etudiant(1, "Bob", "Marley", "3eme nuage a gauche", "paradis", "jamin@with.you", 0, 0, 0,
				true, true);
		list.add(Bob);
		Classe cla = new Classe(1, "Session2020", list);
		Classe retour = service.create(cla);
		assertThat(retour).hasFieldOrPropertyWithValue("nom", "Session2020");
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("nom", "Bob");
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("prenom", "Marley");
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("adresse", "3eme nuage a gauche");
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("ville", "paradis");
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("email", "jamin@with.you");
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("codePostale", 0);
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("cni", 0);
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("telephone", 0);
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("sexe", true);		
		assertThat(retour.getEtudiants().get(0)).hasFieldOrPropertyWithValue("enEtude", true);

	}

	// Test findAll
	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode findAll avec BD vide
	 */
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertThat(service.findAll()).isEmpty();
	}

	// Valide ! (pb avec etudiant)
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode findAll avec BD rempli
	 */
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'TopOfTheTop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (2, 'Terminal2b', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 classes")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
		Classe retour = service.findAll().get(0);
		assertThat(retour).hasFieldOrPropertyWithValue("id", 1);
		assertThat(retour).hasFieldOrPropertyWithValue("nom", "Session2020");
		//assertThat(retour).hasFieldOrPropertyWithValue("etudiants", null);
	}

	// Test findById
	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode findById avec une DB vide
	 */
	@Test
	@DisplayName("Recherche d'une classe par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}
	
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode findById avec un id de recherche egale a zero
	 */
	@Test
	@DisplayName("Recherche d'une classe par id egale à zéro")
	public void testFindByIdWithIdEqualZero_shouldReturnNull() {
		assertThat(service.findById(0)).isNull();
	}

	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode findById avec une classe dans la DB
	 */
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'TopOfTheTop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche d'une classe par id")
	public void testFindById_shouldReturnEtudiant() {
		Classe retour = service.findById(1);
		assertThat(retour).hasFieldOrPropertyWithValue("id", 1);
		assertThat(retour).hasFieldOrPropertyWithValue("nom", "Session2020");
	}

	// Test update
	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode update avec un objet null
	 */
	@Test
	@DisplayName("Update d'une classe null")
	public void testUpdateNullClasse_shouldReturnFalse() {
		Classe cla = null;
		assertThat(service.update(cla)).isFalse();
	}

	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode update avec une DB vide
	 */
	@Test
	@DisplayName("Update d'une classe inexistant dans la bd")
	public void testUpdateInexistantClasse_shouldReturnFalse() {
		Classe cla = new Classe(0, "Session2020", null);
		assertThat(service.update(cla)).isFalse();
	}

	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode update avec objet valide et DB valide
	 */
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'TopOfTheTop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un niveau enregistrer dans la BD")
	public void testUpdateNiveauWithId_shouldReturnTrue() {
		Classe cla = new Classe(1, "Terminal2b", null);
		assertThat(service.update(cla)).isTrue();
	}

	// Test deleteById
	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode deleteById avec id egale a 0
	 */
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'TopOfTheTop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		assertThat(service.deleteById(0)).isFalse();
	}

	// Valide !
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode deleteById avec un id valide et donnée dans la BD
	 */
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'TopOfTheTop')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}

}
