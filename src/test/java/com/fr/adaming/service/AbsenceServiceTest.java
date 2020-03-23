package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;

/**
 * Cette classe teste la couche service de l'entité Absence
 * @author Isaline
 * @since 1.0.X
 *
 */
@SpringBootTest
public class AbsenceServiceTest {

	@Autowired
	private IAbsenceService service;

	// Tests create
	/**
	 * Cette méthode teste la création et la sauvegarde dans la BD d'une absence - conditions invalides (absence nulle)
	 */
	@Test
	@DisplayName("Création d'une Absence null")
	public void testCreatingAbsenceNull_shouldReturnNull() {
		Absence abs = null;
		assertNull(service.create(abs));
	}

	/**
	 * Cette méthode teste la création et la sauvegarde dans la BD d'une absence - conditions invalides (params requis manquants)
	 */
	@Test
	@DisplayName("Création d'une Absence avec param null")
	public void testCreatingAbsenceWithNullParams_shouldReturnNull() {
		Absence abs = new Absence(0, null, null, null, null, null);
		assertNull(service.create(abs));
	}

	/**
	 * Cette méthode teste la création et la sauvegarde dans la BD d'une absence - conditions valides
	 */
	@Test
	@Sql(statements = "INSERT INTO Etudiant (id, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1, 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName("Création d'une Absence correcte")
	public void testCreatingCorrectAbsence_shouldReturnAbsence() {
		Etudiant etu = new Etudiant();
		etu.setId(1);
		etu.setCodePostale(0);
		etu.setCni(0);
		etu.setTelephone(0);
		etu.setSexe(true);
		etu.setEnEtude(true);
		Absence abs = new Absence(0, LocalDate.parse("2020-02-20"), LocalDate.parse("2020-02-20"),
				"J'aime pas les bananes", "On lui à demander de manger des bananes", etu);
		assertTrue(service.create(abs).getDebut().equals(LocalDate.parse("2020-02-20")));
		assertTrue(service.create(abs).getFin().equals(LocalDate.parse("2020-02-20")));
		assertTrue(service.create(abs).getJustification().equals("J'aime pas les bananes"));
		assertTrue(service.create(abs).getDescription().equals("On lui à demander de manger des bananes"));
		assertTrue(service.create(abs).getEtudiant().getId() == 1);
	}

	/**
	 * Cette méthode teste la création et la sauvegarde dans la BD d'une absence - conditions valides sans étudiant lié à l'absence
	 */
	@Test
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName("Création d'une Absence correcte sans étudiant")
	public void testCreatingCorrectAbsenceSansEtudiant_shouldReturnAbsence() {
		Absence abs = new Absence(0, LocalDate.parse("2020-02-20"), LocalDate.parse("2020-02-20"),
				"J'aime pas les bananes", "On lui à demander de manger des bananes");
		assertEquals(abs, service.create(abs));
	}

	// Test findAll
	/**
	 * Cette méthode teste la récupération de la liste de toutes les absences - conditions invalides (liste empty)
	 */
	@Test
	@DisplayName("Demande de la liste vide")
	public void testGetList_shouldReturnEmptyList() {
		assertTrue(service.findAll().isEmpty());
	}

	/**
	 * Cette méthode teste la récupération de la liste de toutes les absences - conditions valides
	 */
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (1, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (2, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Demande de la liste de 2 niveaux")
	public void testGetList_shouldReturnList() {
		assertTrue(service.findAll().size() == 2);
	}

	// Test findById
	/**
	 * Cette méthode teste la récupération d'une absence via son id - conditions invalides (id inexistant dans la BD)
	 */
	@Test
	@DisplayName("Recherche d'Absence par id non existant")
	public void testFindByIdWithInexistantId_shouldReturnNull() {
		assertThat(service.findById(1)).isNull();
	}
	
	/**
	 * Cette méthode teste la récupération d'une absence via son id - conditions invalides (id inexistant dans la BD)
	 */
	@Test
	@DisplayName("Recherche d'Absence par id non existant")
	public void testFindByIdWithIdZero_shouldReturnNull() {
		assertThat(service.findById(0)).isNull();
	}

	/**
	 * Cette méthode teste la récupération d'une absence via son id - conditions valides
	 */
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (1, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Recherche d'Absence par id")
	public void testFindById_shouldReturnAbsence() {
		//Absence abs = new Absence(1, LocalDate.parse("2020-02-20"), LocalDate.parse("2020-02-20"));
		assertTrue(service.findById(1).getId() ==1);
		assertTrue(service.findById(1).getDebut().equals(LocalDate.parse("2020-02-20")));
		assertTrue(service.findById(1).getFin().equals(LocalDate.parse("2020-02-20")));
	}

	// Test update
	/**
	 * Cette méthode teste la modification d'une absence dans la BD - conditions invalides (absence nulle)
	 */
	@Test
	@DisplayName("Update d'une Absence null")
	public void testUpdateNullAbsence_shouldReturnFalse() {
		Absence abs = null;
		assertThat(service.update(abs)).isFalse();
	}

	/**
	 * Cette méthode teste la modification d'une absence dans la BD - conditions invalides (absence inexistante dans la BD)
	 */
	@Test
	@DisplayName("Update d'une Absence inexistant dans la bd")
	public void testUpdateInexistantAbsence_shouldReturnFalse() {
		Etudiant etu = new Etudiant();
		Absence abs = new Absence(0, LocalDate.parse("2020-02-20"), LocalDate.parse("2020-02-20"),
				"J'aime pas les bananes", "On lui à demander de manger des bananes", etu);
		assertThat(service.update(abs)).isFalse();
	}

	/**
	 * Cette méthode teste la modification d'une absence dans la BD - conditions valides
	 */
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (1, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update d'une Absence enregistrer dans la BD")
	public void testUpdateAbsence_shouldReturnTrue() {
		Absence abs = new Absence(1, LocalDate.parse("2020-02-20"), LocalDate.parse("2020-02-20"));
		assertThat(service.update(abs)).isTrue();
	}

	// Test deleteById
	/**
	 * Cette méthode teste la suppression d'une absence via son Id - conditions invalides (id inexistant dans la BD)
	 */
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (1, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdEqualsZero_shouldReturnFalse() {
		Integer id = 0;
		assertThat(service.deleteById(id)).isFalse();
	}
	
	/**
	 * Cette méthode teste la suppression d'une absence via son Id - conditions invalides (id inexistant dans la BD)
	 */
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (1, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id = 0")
	public void testDeleteByIdWithIdInexistant_shouldReturnFalse() {
		Integer id = 5;
		assertThat(service.deleteById(id)).isFalse();
	}

	/**
	 * Cette méthode teste la suppression d'une absence via son Id - conditions valides
	 */
	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (1, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete avec id valid")
	public void testDeleteByIdWithValidId_shouldReturnTrue() {
		Integer id = 1;
		assertThat(service.deleteById(id)).isTrue();
	}

}
