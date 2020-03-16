package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.dto.AbsenceDtoCreate;

@SpringBootTest
@AutoConfigureMockMvc
public class AbsenceControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testCreatingAbsenceWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		AbsenceDtoCreate requestDto = new AbsenceDtoCreate();
		requestDto.setJustification("panne de reveil");
		requestDto.setDescription("est arrivé 2 h en retard !");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/absence/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		AbsenceDtoCreate responseDto = mapper.readValue(responseAsStrig, AbsenceDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("justification", requestDto.getJustification());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("description", requestDto.getDescription());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/absence/{"+id+"}").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		AbsenceDtoCreate responseDto = mapper.readValue(responseAsStrig, AbsenceDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/absence/all").contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		AbsenceDto responseDto = mapper.readValue(responseAsStrig, AbsenceDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	@Test
	public void testUpdateAbsenceWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		AbsenceDtoCreate requestDto = new AbsenceDtoCreate();
		requestDto.setJustification("panne de reveil");
		requestDto.setDescription("est arrivé 2 h en retard !");

		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/classe/id").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		AbsenceDtoCreate responseDto = mapper.readValue(responseAsStrig, AbsenceDtoCreate.class);


		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("justification", requestDto.getJustification());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("description", requestDto.getDescription());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}
	
	@Test
	public void testDeleteByIdWithController_shouldWork () throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/classe/{"+id+"}").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		AbsenceDtoCreate responseDto = mapper.readValue(responseAsStrig, AbsenceDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}


}
