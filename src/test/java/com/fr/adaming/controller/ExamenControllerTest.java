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
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
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

	@Test
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,type) values(1,'ds')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		Integer id = 1;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/id").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testUpdateExamenWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		ExamenDtoCreate requestDto = new ExamenDtoCreate();
		requestDto.setCoefficient(2.5d);
		requestDto.setType("DS");

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

	@Test
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,type) values(1,'ds')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
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
	
	@Test
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
	public void testListByMatiereController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 1;
		// test requete
				String responseAsStrig = mockMvc
						.perform(get("http://localhost:8080/examen/matiere?id="+id).contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				// convertir la reponse JSON en DTO
				ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

				assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

}
