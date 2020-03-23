package com.fr.adaming.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Note;

/**
 * Classe test de la couche Dao de l'entité Note
 * @author Isaline
 * @since 1.0.X
 *
 */
@SpringBootTest
public class NoteDaoTest {
	
	@Autowired
	private INoteDao dao;

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode listByEtudiant (liste des notes d'un étudiant) de la couche DAO de l'entité note
	 * En conditions VALIDES (id d'un étudiant existant et notes dans la BD)
	 */
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, etudiant_id, valeur) VALUES (1, 1, 15)")
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)	
	@Test
	public void testListByEtudiant_shouldReturnListOfNotes() {
		assertThat(dao.listByEtudiant(1)).hasSize(1).hasOnlyElementsOfType(Note.class);
	}
	
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode listByEtudiant (liste des notes d'un étudiant) de la couche DAO de l'entité note
	 * En conditions INVALIDES (id d'un étudiant inexistant et notes dans la BD) - vérifie liste de notes empty
	 */
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) "
			+ "VALUES (1,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, etudiant_id, valeur) VALUES (1, 1, 15)")
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)	
	@Test
	public void testListByEtudiantInexistant_shouldReturnListEmpty() {
		assertThat(dao.listByEtudiant(2)).hasSize(0);
	}
	

}
