package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
@AutoConfigureMockMvc
public class ModuleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testCreatingModuleWithController_shouldWork() throws UnsupportedEncodingException, Exception {

		// preparer le DTO
		ModuleDtoCreate requestDto = new ModuleDtoCreate();
		requestDto.setNom("cours des sixiemes");
		List<Matiere> listeMatiere = new ArrayList<Matiere>();
		Matiere math = new Matiere(1, "math");
		Matiere français = new Matiere(1, "français");
		listeMatiere.add(math);
		listeMatiere.add(français);
		requestDto.setMatiere(listeMatiere);
		
		
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ModuleDtoCreate responseDto = mapper.readValue(responseAsStrig, ModuleDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("matiere", requestDto.getMatiere());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/id").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ModuleDtoCreate responseDto = mapper.readValue(responseAsStrig, ModuleDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testFindAllWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ModuleDto responseDto = mapper.readValue(responseAsStrig, ModuleDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testUpdateModuleWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		
		//preparer le dto
		ModuleDtoCreate requestDto = new ModuleDtoCreate();
		requestDto.setNom("cours des sixiemes");
		List<Matiere> listeMatiere = new ArrayList<Matiere>();
		Matiere math = new Matiere(1, "math");
		Matiere français = new Matiere(1, "français");
		listeMatiere.add(math);
		listeMatiere.add(français);
		requestDto.setMatiere(listeMatiere);
		
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(requestDto);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/id").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ModuleDtoCreate responseDto = mapper.readValue(responseAsStrig, ModuleDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("nom", requestDto.getNom());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("matiere", requestDto.getMatiere());
		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

	@Test
	public void testDeleteByIdWithController_shouldWork() throws UnsupportedEncodingException, Exception {
		int id = 5;
		// convrtir le DTO en Json
		String dtoAsJson = mapper.writeValueAsString(id);

		// test requete
		String responseAsStrig = mockMvc
				.perform(post("http://localhost:8080/module/{"+id+"}").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(dtoAsJson))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// convertir la reponse JSON en DTO
		ModuleDtoCreate responseDto = mapper.readValue(responseAsStrig, ModuleDtoCreate.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");
	}

}
