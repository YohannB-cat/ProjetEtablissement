package com.fr.adaming.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Matiere;

/**
 * Classe test de la couche dao de l'entite matière
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@SpringBootTest
public class MatiereDaoTest {

	@Autowired
	private IMatiereDao daoMatiere;

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode FindMatieresByModule (liste des matières d'un module) de la couche DAO de l'entité matieres
	 * En conditions VALIDES (id d'un module existant et matières dans la BD)
	 */
	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere VALUES (1,'math',5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere VALUES (2,'francais',5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere VALUES (3,'technologie',5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere VALUES (4,'physique',5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere WHERE module_id = 5", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id =5", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Test find Liste matieres by module")
	public void testFindMatiereByModule_shouldWork() {
		List<Matiere> listeMatieres = daoMatiere.findMatiereByMatieres(5);
		assertThat(listeMatieres).isNotNull();
		assertThat(listeMatieres).hasSize(4);
		assertThat(listeMatieres.get(0)).hasFieldOrPropertyWithValue("nom", "math");

	}

}
