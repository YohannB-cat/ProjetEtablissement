package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.dto.MatiereDtoCreate;

@SpringBootTest
@AutoConfigureMockMvc
public class MatiereControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testCreatingMatiereWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		MatiereDtoCreate requestDto = new MatiereDtoCreate();
		requestDto.setNom("français");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		MatiereDtoCreate responseDto = mapper.readValue(responseAsStrig, MatiereDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/id").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		MatiereDtoCreate responseDto = mapper.readValue(responseAsStrig, MatiereDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		MatiereDto responseDto = mapper.readValue(responseAsStrig, MatiereDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	@Test
	public void testUpdateMatiereWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		MatiereDtoCreate requestDto = new MatiereDtoCreate();
		requestDto.setNom("math");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		MatiereDtoCreate responseDto = mapper.readValue(responseAsStrig, MatiereDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	@Test
	public void testDeleteByIdWithController_shouldWork () throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/matiere/{"+id+"}").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		MatiereDtoCreate responseDto = mapper.readValue(responseAsStrig, MatiereDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

}
