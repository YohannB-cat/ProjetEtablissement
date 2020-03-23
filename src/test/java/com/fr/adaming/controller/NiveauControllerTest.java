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
import com.fr.adaming.dto.NiveauDto;
import com.fr.adaming.dto.NiveauDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Tests de la classe NiveauController
 * @author Flavien
 * @since 1.0.x
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NiveauControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode CreatingNiveauWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Test
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingNiveauWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		NiveauDto requestDto = new NiveauDto();
		requestDto.setNom("sixieme");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/niveau/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");

		String niveauString = mapper.writeValueAsString(responseDto.getObject());
		NiveauDtoCreate niveauDtoCreate = mapper.readValue(niveauString, NiveauDtoCreate.class);
		assertThat(niveauDtoCreate).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
	}
	
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode FindByExistingIdWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Sql(statements = "INSERT INTO niveau (id, nom) VALUES (5, 'premiere')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Find by valid id")
	public void testFindByExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/niveau/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode FindByNotExistingIdWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Test
	@DisplayName("Find by not existing id")
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testFindByNotExistingIdWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/niveau/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode FindAllWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/niveau/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");

	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode UpdateNiveauExistingWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Sql(statements = "INSERT INTO niveau (id, nom) VALUES (156165, 'premiere')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Update by existing id")
	public void testUpdateNiveauExistingWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		NiveauDtoCreate requestDto = new NiveauDtoCreate();
		requestDto.setNom("sixième");
		requestDto.setId(156165);
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/niveau").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode UpdateNiveauNotExistingWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Test
	@DisplayName("Find by not existing id")
	public void testUpdateNiveauNotExistingWithController_shouldNotWork()
			throws UnsupportedEncodingException, Exception {
		NiveauDtoCreate requestDto = new NiveauDtoCreate();
		requestDto.setNom("sixième");
		requestDto.setId(156165);
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/niveau").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode DeleteByExistingIdWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Sql(statements = "INSERT INTO niveau (id, nom) VALUES (5, 'premiere')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Delete by existing id")
	public void testDeleteByExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/niveau/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode DeleteByNotExistingIdWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Test
	@DisplayName("Delete by not existing id")
	public void testDeleteByNotExistingIdWithController_shouldNNotWork()
			throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/niveau/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	/**
	 * Cette méthode vérifie le fonctionnement de la méthode FindClassByExistingIdWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Sql(statements = "INSERT INTO niveau (id, nom) VALUES (5, 'premiere')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO classe (id, nom, id_niveau) VALUES (3, '1D',5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO classe (id, nom, id_niveau) VALUES (6, 'TA',5)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Find niveau by existing classe")
	public void testFindClassByExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
	
		// test requete
		String responseAsStrig = mockMvc.perform(get("http://localhost:8080/niveau/classe/" + id)).andDo(print())
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	/**
	 * Cette méthode vérifie le fonctionnement de la méthode FindClassByNotExistingIdWithController
	 * @throws UnsupportedEncodingException en cas d'erreur de conversion JSON - String
	 * @throws Exception en cas d'erreur général
	 */
	@Test
	@Sql(statements = "DELETE FROM classe", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM niveau", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@DisplayName("Find niveau by not existing classe")
	public void testFindClassByNotExistingIdWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 50;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);
	
		// test requete
		String responseAsStrig = mockMvc.perform(get("http://localhost:8080/niveau/classe/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(dtoAsJson)).andDo(print())
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
