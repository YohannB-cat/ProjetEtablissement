package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
@AutoConfigureMockMvc
public class ModuleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	// **********************************************************************
	// TEST CREATING MODULE

	// Unable to fnd entite.Matiere with id = 1
	@Test
	@DisplayName("Creation module")
	public void testCreatingModuleWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		ModuleDtoCreate requestDto = new ModuleDtoCreate();
		requestDto.setNom("cours des sixiemes");


		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);

		String modString = mapper.writeValueAsString(responseDto.getObject());
		ModuleDtoCreate respModule = mapper.readValue(modString, ModuleDtoCreate.class);

		assertFalse(responseDto.isError());

		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("matiere", requestDto.getMatiere());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Création module avec champ null")
	public void testCreatingModuleWithNullAttribute_ShouldReturnError() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		ModuleDtoCreate requestDto = new ModuleDtoCreate();

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertTrue(responseDto.isError());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");

	}

	// **********************************************************************
	// TEST FIND BY ID MODULE

	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 5", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@DisplayName("Find one module")
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/module/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	@DisplayName("Find By Id with no DB - shoudl return fail")
	public void testFindByIdWithIncorrectId_ShouldReturnFail() throws UnsupportedEncodingException, Exception {
		int id = 5;

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/module/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	// **********************************************************************
	// TEST FIND ALL MODULE

	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO Module VALUES (6,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE name = 'sixieme'", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@DisplayName("Find all module")
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/module/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	// **********************************************************************
	// TEST UPDATE MODULE

	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = '5'", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@DisplayName("Update module")
	public void testUpdateModuleWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le dto
		ModuleDtoCreate requestDto = new ModuleDtoCreate();
		requestDto.setNom("cinquième");
		requestDto.setId(5);


		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertFalse(responseDto.isError());

		String modString = mapper.writeValueAsString(responseDto.getObject());
		ModuleDtoCreate respModule = mapper.readValue(modString, ModuleDtoCreate.class);

		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("matiere", requestDto.getMatiere());

	}

	@Test
	@DisplayName("Update matiere with no")
	public void testUpdateModuleWithNoEntityDb_ShouldReturnFail() throws UnsupportedEncodingException, Exception {

		// preparer le dto
		ModuleDtoCreate requestDto = new ModuleDtoCreate();
		requestDto.setNom("cinquième");
		requestDto.setId(5);

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
		assertFalse(responseDto.isError());

	}

	// **********************************************************************
	// TEST DELETE MODULE

	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Module WHERE id = 5", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	@DisplayName("Delete module")
	public void testDeleteByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/module/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	@Test
	@DisplayName("Delete Module with no DB")
	public void testDeleteByIdWithNoDB_shouldReturnFail() throws UnsupportedEncodingException, Exception {
		
		int id = 5;

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/module/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
