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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.entity.Module;

@SpringBootTest
@AutoConfigureMockMvc
public class ModuleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

//	@Test
//	@DisplayName ("Creation matiere")
//	public void testCreatingModuleWithController_shouldWork() throws UnsupportedEncodingException, Exception {
//
//		// preparer le DTO
//		ModuleDtoCreate requestDto = new ModuleDtoCreate();
//		requestDto.setNom("cours des sixiemes");
//		List<Matiere> listeMatiere = new ArrayList<Matiere>();
//		Matiere math = new Matiere(1, "math");
//		Matiere français = new Matiere(2, "français");
//		listeMatiere.add(math);
//		listeMatiere.add(français);
//		requestDto.setMatiere(listeMatiere);
//		
//		
//		// convrtir le DTO en Json
//		String dtoAsJson = mapper.writeValueAsString(requestDto);
//
//		// test requete
//		String responseAsStrig = mockMvc
//				.perform(post("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
//						.content(dtoAsJson))
//				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		// convertir la reponse JSON en DTO
//		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
//
//		assertNotNull(responseDto);
//
//		String modString = mapper.writeValueAsString(responseDto.getObject());
//		ModuleDtoCreate respModule = mapper.readValue(modString, ModuleDtoCreate.class);
//		
//		assertFalse(responseDto.isError());
//		
//		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
//		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("matiere", requestDto.getMatiere());
//		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
//		
//		
//	}
//
//	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Module WHERE id = 5",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Test
//	@DisplayName ("Find one matiere")
//	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
//		int id = 5;
//		// convrtir le DTO en Json
//		String dtoAsJson = mapper.writeValueAsString(id);
//
//		// test requete
//		String responseAsStrig = mockMvc
//				.perform(get("http://localhost:8080/module/id").contentType(MediaType.APPLICATION_JSON_VALUE)
//						.content(dtoAsJson))
//				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		// convertir la reponse JSON en DTO
//		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
//
//		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
//	}
//
//	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO Module VALUES (6,'sixieme')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Module WHERE name = 'sixieme'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Test
//	@DisplayName ("Find all matiere")
//	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
//		// test requete
//		String responseAsStrig = mockMvc
//				.perform(get("http://localhost:8080/module/all").contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		// convertir la reponse JSON en DTO
//		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
//
//		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
//	}
//	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Module WHERE id = '5'",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Test
//	@DisplayName ("Update matiere")
//	public void testUpdateModuleWithController_shouldWork() throws UnsupportedEncodingException, Exception {
//		
//		//preparer le dto
//		ModuleDtoCreate requestDto = new ModuleDtoCreate();
//		requestDto.setNom("cinquième");
//		requestDto.setId(5);
//		List<Matiere> listeMatiere = new ArrayList<Matiere>();
//		Matiere math = new Matiere(1, "math");
//		Matiere français = new Matiere(2, "français");
//		listeMatiere.add(math);
//		listeMatiere.add(français);
//		requestDto.setMatiere(listeMatiere);
//		
//		// convrtir le DTO en Json
//		String dtoAsJson = mapper.writeValueAsString(requestDto);
//
//		// test requete
//		String responseAsStrig = mockMvc
//				.perform(put("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
//						.content(dtoAsJson))
//				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		// convertir la reponse JSON en DTO
//		ResponseDto responseDto = mapper.readValue(responseAsStrig, ResponseDto.class);
//
//		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
//		assertFalse(responseDto.isError());
//		
//		String modString = mapper.writeValueAsString(responseDto.getObject());
//		ModuleDtoCreate respModule = mapper.readValue(modString, ModuleDtoCreate.class);
//		
//		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
//		assertThat(respModule).isNotNull().hasFieldOrPropertyWithValue("matiere", requestDto.getMatiere());
//		
//		
//	}
//
//	@Sql(statements = "INSERT INTO Module VALUES (5,'sixieme')",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM Module WHERE id = 5",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Test
//	@DisplayName ("Delete matiere")
//	public void testDeleteByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
//		int id = 5;
//		// convrtir le DTO en Json
//		String dtoAsJson = mapper.writeValueAsString(id);
//
//		// test requete
//		String responseAsStrig = mockMvc
//				.perform(delete("http://localhost:8080/module/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)
//						.content(dtoAsJson))
//				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		// convertir la reponse JSON en DTO
//		ResponseDto responseDto = mapper.readValue(responseAsStrig,ResponseDto.class);
//
//		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
//	}

}
