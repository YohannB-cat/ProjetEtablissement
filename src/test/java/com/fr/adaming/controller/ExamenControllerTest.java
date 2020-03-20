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

@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

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

	@Test
	@DisplayName("Examen par id valide")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,date,type,coefficient) values(5,'1994-02-03','ds',2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		Integer id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	@Test
	@DisplayName("Examen par id non valide")
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen(id,date,type,coefficient) values(1,'1994-02-03','ds',2.2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindByIdWithoutController_shouldNotWork() throws UnsupportedEncodingException, Exception {
		Integer id = 1;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);
		// test requete
		String responseAsStrig = mockMvc
				.perform(get("http://localhost:8080/examen/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

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
