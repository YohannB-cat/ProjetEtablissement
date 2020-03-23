package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Classe test de la couche controller de l'entité Examen
 * @author Yohann
 * @since 1.0.x
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Test de la méthode create avec un examen valide
	 * Doit retourner une requete de statut 200 contenant l'objet créé et un message success
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Creation Examen")
	public void testCreatingExamenWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		ExamenDtoCreate requestDto = new ExamenDtoCreate();
		requestDto.setCoefficient(2.5d);
		requestDto.setType("DS");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/examen/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");

	}
	/**
	 * Test de la méthode create avec un examen non valide (attribut nom avec trop de caractères)
	 * Doit retourner une requete de statut 400 contenant un objet null et un message d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Creation Examen non valide")
	public void testCreatingNonValidExamenWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		ExamenDtoCreate requestDto = new ExamenDtoCreate();
		requestDto.setCoefficient(2.5d);
		requestDto.setType("DSpppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/examen/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	/**
	 * Test de la méthode findByID avec l'id d'un examen existant danas la DB
	 * Doit retourner une requete de statut 200 contenant l'objet examen recherché et un message de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Examen par id valide")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,date,type,coefficient) values(5,'1994-02-03','ds',2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		Integer id = 5;
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/"+id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	/**
	 * Test de la méthode findById avec l'id d'un examen qui n'existe pas
	 * Doit retourner une requete de statut 400 contenant un objet null et un message d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Examen par id non valide")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testFindByIdWithoutController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		Integer id = 10;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
	
	/**
	 * Test de la méthode findById avec un id = 0
	 * Doit retourner une requete de statut 400 contenant un objet null et un message d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Examen par id= 0")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,date,type,coefficient) values(1,'1994-02-03','ds',2.2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindByIdWithoutControllerNULLId_ShouldReturnFail() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/"+0).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
	

	/**
	 * Test de la méthode findAll sans examens dans la DB
	 * Doit retourner une requete de statut 200 contenant une liste vide et un message de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Tous les examens")
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Test de la méthode update avec un id inconnue dans la DB
	 * Doit retourner une requete de statu 400 contenant un objet null et un message d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Update d'un examen inexistant")
	@Sql(statements = "delete from examen",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testUpdateNonExistingExamenWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		ExamenDtoCreate requestDto = new ExamenDtoCreate();
		requestDto.setId(2);
		requestDto.setCoefficient(2.5d);
		requestDto.setType("DS");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/examen/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
	/**
	 * Test de la méthode update avec un examen qui existe dans la DB
	 * Doit retourner une requete de statu 200 contenant l'objet modifié et un message de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Update d'un examen existant")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,type) values(1,'ds')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateExistingExamenWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		ExamenDtoCreate requestDto = new ExamenDtoCreate();
		requestDto.setId(1);
		requestDto.setCoefficient(2.5d);
		requestDto.setType("DM");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/examen/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Test de la méthode deleteById avec un examen existant
	 * Doit retourner une requete de statut 200 contenant un objet null et un message de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Suppression d'un examen existant")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,type) values(1,'ds')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteByIdWithExistingId_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 1;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/examen/" + id )
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	/**
	 * Test de la méthode deleteById avec un id inconnu dans la DB
	 * Doit retourner une requete de statut 400 contenant un objet null et un message d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Suppression d'un examen inexistant")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteByIdWithoutExistingId_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 1;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/examen/" + id )
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andDo(print()).andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
	
	/**
	 * Test de la méthode ListByMatiere avec une matiere existante et contenant des examens
	 * Doit retourner une requete de statut 200 contenant une liste d'examens appartenant à la matière demandée et un message de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Liste des examens par matiere existante")
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Examen (id, type) VALUES (1, 'exam1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Examen (id, type) VALUES (2, 'exam2')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module (id, nom) VALUES (1, 'module1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Matiere (id, nom,module_id) VALUES (1, 'matiere1',1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id,valeur,examen_id) VALUES (1, 1,12,1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Note (id, module_id,valeur,examen_id) VALUES (2, 1,13,2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testListByMatiereControllerWithExistingId_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 1;
		// test requete
				String responseAsStrig = mockMvc
						.perform(get("http://localhost:8080/examen/matiere?id="+id).contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				// convertir la reponse JSON en DTO
				ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

				assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}	
	/**
	 * Test de la méthode ListByMatiere avec une matiere inexistante
	 * Doit retourner une requete de statut 400 contenant un objet null et un message d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@DisplayName("Liste des examens par matiere inexistante")
	public void testListByMatiereControllerWithoutExistingId_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 10;
		// test requete
				String responseAsStrig = mockMvc
						.perform(get("http://localhost:8080/examen/matiere?id="+id).contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
				// convertir la reponse JSON en DTO
				ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

				assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
