package com.fr.adaming.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
public class NiveauDaoTest {
	
	@Autowired
	private INiveauDao dao;
	
	@Sql(statements = "INSERT INTO Niveau (id, nom) VALUES (1, 'Maternel')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (2, 'Terminal2b', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Classe (id, nom, classes_id) VALUES (1, 'Session2020', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe WHERE id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Niveau WHERE id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindListClasseByIdNiveau_ShouldReturnList() {
		assertThat(dao.findListClasseByIdNiveau(1)).hasSize(2);
		
	}
}
