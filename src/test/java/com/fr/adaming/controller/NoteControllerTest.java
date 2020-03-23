package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Classe test de la couche controller de l'entité note
 * @author clara
 * @since 1.0.X
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Teste la création d'une note et vérifie que le controller retourne bien la note qu'on lui a demandé de créer
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Test
	public void testCreatingNoteWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		NoteDtoCreate requestDto = new NoteDtoCreate();
		requestDto.setValeur(15.3f);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/note/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		
		
		String noteString = mapper.writeValueAsString(responseDto.getObject());
		NoteDtoCreate noteDtoCreate = mapper.readValue(noteString, NoteDtoCreate.class);
		assertThat(noteDtoCreate).isNotNull().hasFieldOrPropertyWithValue("valeur", requestDto.getValeur());
	}

	/**
	 * Teste la recherche d'une note par un identifiant connu dans la base de donnée et vérifie le succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Sql(statements = "INSERT INTO note (id, valeur) values (5, 18)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByIdExistingWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/note/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(dtoAsJson)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	/**
	 * Teste la recherche d'une note par un identifiant inconnu dans la base de donnée et vérifie que ça ne fonctionne pas
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Test
	public void testFindByIdNotExistingWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/note/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	/**
	 * Teste la recherche de tout les notes et vérifie le succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/note/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Teste la modification d'une note connue dans la base de donnée et vérifie le succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Sql(statements = "INSERT INTO note (id, valeur) values (25, 18)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateExistingNoteWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		NoteDtoCreate requestDto = new NoteDtoCreate();
		requestDto.setValeur(15.3f);
		requestDto.setId(25);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/note").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	/**
	 * Teste la modification d'une note inconnue dans la base de donnée et vérifie l'échec
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Test
	public void testUpdateNotExistingNoteWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		NoteDtoCreate requestDto = new NoteDtoCreate();
		requestDto.setValeur(15.3f);
		requestDto.setId(25);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/note").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
	
	/**
	 * Teste la suppression d'une note connue dans la base de donnée et vérifie le succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Sql(statements = "INSERT INTO note (id, valeur) values (5, 18)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testDeleteByExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/note/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	/**
	 * Teste la suppression d'une note inconnue dans la base de donnée et vérifie l'échec
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Test
	public void testDeleteByNotExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/note/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}
	
	/**
	 * Teste la recherche de notes par l'identifiant d'un étudiant connu et vérifie le succès
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Sql(statements = "INSERT INTO etudiant (id, nom,cni,code_postale,telephone,sexe,en_etude) values (24, 'bob',11,11,11,1,1)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO note (id, valeur, etudiant_id) values (5, 18,24)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByIdEtudiantExistingWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 24;

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/note/etudiant/"+id))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	/**
	 * Teste la recheche de notes par l'identifiant d'un étudiant inconnu dans la base de donnée et vérifie l'échec
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'exception générale
	 */
	@Test
	public void testFindByIdEtudiantNotExistingWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 5;

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/note/etudiant/"+id))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO

		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
