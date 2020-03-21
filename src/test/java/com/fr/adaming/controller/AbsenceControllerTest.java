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
import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AbsenceControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingAbsenceWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		AbsenceDtoCreate requestDto = new AbsenceDtoCreate();
		requestDto.setDebut("2020-02-20");
		requestDto.setFin("2020-02-20");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/absence/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);

		String absString = mapper.writeValueAsString(responseDto.getObject());
		AbsenceDtoCreate respAbsDtoCreate = mapper.readValue(absString, AbsenceDtoCreate.class);

		assertThat(respAbsDtoCreate).isNotNull().hasFieldOrPropertyWithValue("debut", requestDto.getDebut());
		assertThat(respAbsDtoCreate).isNotNull().hasFieldOrPropertyWithValue("fin", requestDto.getFin());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertFalse(responseDto.isError());
	}

	@Test
	@DisplayName("test creation absence withtout attribute")
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingAbsenceWithoutAttribute_shouldReturnFail() throws UnsupportedEncodingException, Exception {
		// preparer le DTO
		AbsenceDtoCreate requestDto = new AbsenceDtoCreate();
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/absence/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
		
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (5, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/absence/one/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindByIdInvalid_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/absence/one/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/absence/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (5, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateAbsenceWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		AbsenceDtoCreate requestDto = new AbsenceDtoCreate();
		requestDto.setId(5);
		requestDto.setDebut("2020-02-28");
		requestDto.setFin("2020-02-28");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/absence").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testUpdateAbsenceWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		AbsenceDtoCreate requestDto = new AbsenceDtoCreate();
		requestDto.setId(15);
		requestDto.setDebut("2020-02-28");
		requestDto.setFin("2020-02-28");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(put("http://localhost:8080/absence").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertNotNull(responseDto);
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

	@Sql(statements = "INSERT INTO Absence (id, debut, fin) VALUES (5, '2020-02-20', '2020-02-20')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeleteByExistingIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/absence/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testDeleteByNotExistingIdWithController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		int id = 28;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(delete("http://localhost:8080/absence/" + id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "FAIL");
	}

}
