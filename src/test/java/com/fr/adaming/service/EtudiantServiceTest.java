package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Etudiant;

@SpringBootTest
public class EtudiantServiceTest {

	@Autowired
	private IEtudiantService service;

	// Tests create
	@Test
	@DisplayName("Création d'un etudiant null")
	public void testCreatingEtudiantNull_shouldReturnNull() {
		Etudiant etu = null;
		assertNull(service.create(etu));
	}

	@Test
	@DisplayName("Création d'un etudiant avec param null")
	public void testCreatingEtudiantWithNullName_shouldReturnEtudiant() {
		Etudiant etu = new Etudiant(0, null, null, null, null, null, 0, 0, 0, true, true);
		assertThat(service.create(etu)).isEqualTo(etu);
	}

	@Test
	@DisplayName("Création d'un etudiant avec correct")
	public void testCreatingCorrectEtudiant_shouldReturnEtudiant() {
		Etudiant etu = new Etudiant(0, "Bob", "Marley", "3eme nuage a gauche", "paradis", "jamin@with.you", 0, 0, 0,
				true, true);
		assertThat(service.create(etu)).isEqualTo(etu);
	}

	// Test findAll
	@Test
	@Sql(statements = "DELETE FROM Etudiant", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		System.out.println(service.findAll());
		assertTrue(service.findAll().isEmpty());
	}

	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (2,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 chats")
	public void testGetList_shouldReturnList() {
		assertThat(service.findAll()).hasSize(2);
	}

	// Test findById
	@Test
	@DisplayName("Recherche d'étudiant par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}
	
	@Test
	@DisplayName("Recherche d'étudiant par id égal à 0")
	public void testFindByIdWithZeroId_shouldReturnNull() {
		assertThat(service.findById(0)).isNull();
	}

	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche d'etudiant par id")
	public void testFindById_shouldReturnEtudiant() {
		Etudiant cetu = service.findById(1);
		assertTrue(cetu.getId()==1);
		assertTrue(cetu.getCodePostale()==0);
		assertTrue(cetu.getCni()==0);
		assertTrue(cetu.getTelephone()==0);
		assertTrue(cetu.isSexe());
		assertTrue(cetu.isEnEtude());
		assertEquals(cetu.getNom(),"Bob");
		assertEquals(cetu.getPrenom(),"Marley");
		assertEquals(cetu.getAdresse(),"3eme nuage a gauche");
		assertEquals(cetu.getVille(),"paradis");
		assertEquals(cetu.getEmail(),"jamin@with.you");
		
	}

	// Test update
	@Test
	@DisplayName("Update d'un etudiant null")
	public void testUpdateNullEtudiant_shouldReturnFalse() {
		Etudiant etu = null;
		assertThat(service.update(etu)).isFalse();
	}

	@Test
	@DisplayName("Update d'un etudiant inexistant dans la bd")
	public void testUpdateInexistantEtudiant_shouldReturnFalse() {
		Etudiant etu = new Etudiant(0, "Bob", "Marley", "3eme nuage a gauche", "paradis", "jamin@with.you", 0, 0, 0,
				true, true);
		assertThat(service.update(etu)).isFalse();
	}

	@Sql(statements = "INSERT INTO Etudiant (nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES ('Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'un etudiant enregistrer dans la BD")
	public void testUpdateEtudiantWithId_shouldReturnTrue() {
		Etudiant etu = new Etudiant(1, "Donkey", "Kong", "3eme tonneau a gauche", "jungle", "jaime@les.bananes", 0, 0,
				0, true, true);
		assertThat(service.update(etu)).isTrue();
	}

	// Test deleteById
	@Sql(statements = "INSERT INTO Etudiant (nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES ('Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}

	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Etudiant WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}
}
