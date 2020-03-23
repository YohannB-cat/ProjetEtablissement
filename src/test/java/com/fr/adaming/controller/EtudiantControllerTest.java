package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Classe test de la couche controller pour l'entité Etudiant
 * @author Yohann
 * @since 1.0.x
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Test de la méthode create avec un etudiant valide
	 * Doit retourner une requete de statut 200 contenant l'objet etudiant créé et un message de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	@Sql(statements = "Delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiantWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		EtudiantDto requestDto = new EtudiantDto();
		requestDto.setNom("rembert");
		requestDto.setPrenom("maxime");
		requestDto.setVille("Annecy");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO

		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);

		String etuString = mapper.writeValueAsString(responseDto.getObject());
		EtudiantDtoCreate respEtudiant = mapper.readValue(etuString, EtudiantDtoCreate.class);

		assertThat(respEtudiant).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(respEtudiant).isNotNull().hasFieldOrPropertyWithValue("prenom", requestDto.getPrenom());
		assertThat(respEtudiant).isNotNull().hasFieldOrPropertyWithValue("ville", requestDto.getVille());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertFalse(responseDto.isError());
	}


	/**
	 * Test de la méthode findById  avec un etudiant inexistant
	 * Doit retourner une requete de statut 400 contenant un objet null et un mesasge d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	public void testFindByIdInvalid_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 58465;
		// convrtir le DTO en Json

		// test requete
		String responseAsStrig = mockMvc.perform(get("http://localhost:8080/etudiant/" + id)).andDo(print())
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	/**
	 * Test de la méthode findById  avec un etudiant existant
	 * Doit retourner une requete de statut 200 contenant l'objet recherché et un mesasge de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) VALUES (14,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByIdValid_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 14;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/etudiant/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	/**
	 * Test de la méthode findAll  sans etudiants enregistrés
	 * Doit retourner une requete de statut 200 contenant une liste vide et un mesasge de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/etudiant/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	/**
	 * Test de la méthode update  avec un etudiant existant
	 * Doit retourner une requete de statut 200 contenant un objet null et un mesasge de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) VALUES (14,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateEtudiantExistingWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		EtudiantDtoCreate requestDto = new EtudiantDtoCreate();
		requestDto.setNom("rembert");
		requestDto.setId(14);
		requestDto.setPrenom("maxime");
		requestDto.setVille("Annecy");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");

	}

	/**
	 * Test de la méthode update  avec un etudiant inexistant
	 * Doit retourner une requete de statut 400 contenant un objet null et un mesasge d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	public void testUpdateEtudiantNotExistingWithController_shouldNotWork()
			throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		EtudiantDtoCreate requestDto = new EtudiantDtoCreate();
		requestDto.setNom("rembert");
		requestDto.setId(14);
		requestDto.setPrenom("maxime");
		requestDto.setVille("Annecy");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");

	}

	/**
	 * Test de la méthode delete  avec un etudiant existant
	 * Doit retourner une requete de statut 200 contenant un objet null et un mesasge de succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Sql(statements = "INSERT INTO Etudiant (id, nom, prenom, adresse, ville, email, code_postale, cni, telephone, sexe, en_etude) VALUES (5,'Bob', 'Marley', '3eme nuage a gauche', 'paradis', 'jamin@with.you', 0, 0, 0, true, true)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "Delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeleteByExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/etudiant/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Test de la méthode delete  avec un etudiant inexistant
	 * Doit retourner une requete de statut 400 contenant un objet null et un mesasge d'erreur
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur générale
	 */
	@Test
	public void testDeleteByNotExistingIdWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 28;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/etudiant/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
}
