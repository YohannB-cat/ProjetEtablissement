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
import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ClasseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	// **********************************************************************
	// TEST CREATE

	@Test
	@DisplayName("Test create OK")
	public void testCreatingClasseWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		ClasseDtoCreate requestDto = new ClasseDtoCreate();
		requestDto.setNom("6B");
		requestDto.setId(10);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/classe/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ClasseDtoCreate classeResponsDto = mapper.readValue(responseAsStrig, ClasseDtoCreate.class);

		assertThat(classeResponsDto).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());

		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Test create with FAIL")
	public void testCreatingWithFail_SHouldReturn400() throws UnsupportedEncodingException, Exception {
		// preparer le DTO
		ClasseDtoCreate requestDto = new ClasseDtoCreate();

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/classe/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");

	}

	// **********************************************************************
	// TEST FIND BY

	@Test
	@DisplayName("Find by Id Ok")
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/classe/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Test find by Id == 0")
	public void testFindByIdWithController_ReturnFAIL() throws UnsupportedEncodingException, Exception {
		int id = 0;

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/classe/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
	}

	// **********************************************************************
	// TEST FIND ALL

	@Test
	@DisplayName("test Find All OK")
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/classe/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("test Find All bad request")
	public void testFindAllWithController_ReturnFAIL() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/classe/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
	}

	// **********************************************************************
	// TEST UPDATE

	@Test
	@DisplayName("Test Update OK")
	public void testUpdateClasseWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		ClasseDtoCreate requestDto = new ClasseDtoCreate();
		requestDto.setNom("6B");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/classe/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Test UpdateFAIL")
	public void testUpdateClasseWithController_ReturnFAIL() throws UnsupportedEncodingException, Exception {
		ClasseDtoCreate requestDto = new ClasseDtoCreate();

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/classe/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
	}

	// **********************************************************************
	// TEST DELETE
	@Sql(statements = "INSERT INTO Classe (id,nom) VALUES (5,'sixiemeB')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@DisplayName("Test Delete by ID OK")
	public void testDeleteByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/classe/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Test Delete by NOT OK")
	public void testDeleteByIdWithController_ReturnFAIL() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/classe/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
