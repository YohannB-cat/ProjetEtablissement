package com.fr.adaming.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

/**
 * test de la méthode FindListClasseByIdNiveau de NiveauDao
 * @author Flavien
 * @since 1.0.x
 *
 */
@SpringBootTest
public class NiveauDaoTest {
	
	@Autowired
	private INiveauDao dao;
	
	/**
	 * Test de la methode de recherche des Classes du même Niveau, par id de ce Niveau.
	 * Instanciation d'un Niveau, et de deux Classes avant le test.
	 * 
	 * On test la méthode avec l'id du Niveau crée. Elle est sensé nous renvoyé la liste des deux classes que l'on à crée avant et ou l'on a placé la dépendance au niveau.
	 */
	// Valide !
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'Maternel')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (2, 'Terminal2b', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, id_niveau) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindListClasseByIdNiveau_ShouldReturnList() {
		assertThat(dao.findListClasseByIdNiveau(1)).hasSize(2);
	}
}
