package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereCreateConverterTest {
	
	@Autowired
	public IConverter<Matiere, MatiereDtoCreate> convert;
	
	//TEST DTO TO ENTITE
	
		@Test
		public void testDtoToEntiteValid_shouldReturnEntite() {
			MatiereDtoCreate dto = new MatiereDtoCreate("math");
			
			Matiere mat = convert.dtoToEntite(dto);

			assertThat(mat).hasFieldOrPropertyWithValue("nom", dto.getNom());


		}
		
		@Test
		public void testDtoToEntiteWithBlankValue_shouldReturnNull() {
			MatiereDtoCreate dto = new MatiereDtoCreate();
			
			Matiere mat = convert.dtoToEntite(dto);
			
			assertThat(mat).isNull();
			
		}

		@Test
		public void testDtoToEntiteNull_shouldReturnNull() {
			MatiereDtoCreate dto = null;
			Matiere mat = convert.dtoToEntite(dto);
			
			assertThat(mat).isNull();
		}
		
		//TEST LISTE DTO TO ENTITE
		
		@Test
		public void testListDtoToEntiteValid_shouldReturnEntite() {
			List<MatiereDtoCreate> listeMatiereDto = new ArrayList<>();;
			MatiereDtoCreate dto1 = new MatiereDtoCreate("math");
			MatiereDtoCreate dto2 = new MatiereDtoCreate("français");
			listeMatiereDto.add(dto1);
			listeMatiereDto.add(dto2);
			
			List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
			
			assertThat(listeEntite).hasSize(2);
			assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto1.getNom());
			assertThat(listeEntite.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
			
		}
		
		@Test
		public void testListDtoToEntiteWithOneBlankDto_shouldReturnNotBlankListItem() {
			List<MatiereDtoCreate> listeMatiereDto = new ArrayList<>();;
			MatiereDtoCreate dto1 = new MatiereDtoCreate();
			MatiereDtoCreate dto2 = new MatiereDtoCreate("math");
			listeMatiereDto.add(dto1);
			listeMatiereDto.add(dto2);
			
			List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
			
			assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
		}
		
		@Test
		public void testListDtoToEntiteNull_shouldReturnNull() {
			List<MatiereDtoCreate> listeMatiereDto = null;
			List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
			
			assertThat(listeEntite).isEmpty();
		}
		
		//TEST  ENTITE TO DTO
		
		@Test
		public void testEntiteToDtoValid_shouldReturnDto() {
			Matiere mat = new Matiere("math");
			MatiereDtoCreate dtoMAt = convert.entiteToDto(mat);
			
			assertThat(dtoMAt).hasFieldOrPropertyWithValue("nom", mat.getNom());
		}
		
		@Test
		public void testEntiteToDtoWithBlanckEntite_shouldReturnNull() {
			Matiere mat = new Matiere();
			
			MatiereDtoCreate dtoMat = convert.entiteToDto(mat);
			
			assertThat(dtoMat).isNull();
		}
		
		@Test
		public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
			Matiere mat = null;
			
			MatiereDtoCreate dtoMat = convert.entiteToDto(mat);
			
			assertThat(dtoMat).isNull();
		}
		
		//TEST LISTE ENTITE  TO DTO
		
		@Test
		public void testListEntiteToDtoValid_shouldReturnEntite() {
			List<Matiere> listeMat = new ArrayList<Matiere>();
			Matiere mat1 = new Matiere("math");
			Matiere mat2 = new Matiere("français");
			listeMat.add(mat1);
			listeMat.add(mat2);
			
			List<MatiereDtoCreate> dtoListe = convert.listEntiteToDto(listeMat);
			
			assertThat(dtoListe).hasSize(listeMat.size());
			assertThat(dtoListe.get(0)).hasFieldOrPropertyWithValue("nom", mat1.getNom());
			assertThat(dtoListe.get(1)).hasFieldOrPropertyWithValue("nom", mat2.getNom());
		}
		
		@Test
		public void testListEntiteToDtoWithBlankItem_shouldReturnListWithNoBlankItem() {
			List<Matiere> listeMat = new ArrayList<>();
			Matiere dto1 = new Matiere();
			Matiere dto2 = new Matiere("math");
			listeMat.add(dto1);
			listeMat.add(dto2);
			
			List<MatiereDtoCreate> dtoLiist = convert.listEntiteToDto(listeMat);
			
			assertThat(dtoLiist.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
		}

		@Test
		public void testListEntiteToDtoWithNullList_shouldReturnNull() {
			List<Matiere> listeMat = null;
			List<MatiereDtoCreate> dtoLiist = convert.listEntiteToDto(listeMat);
			
			assertThat(dtoLiist).isEmpty();
			
		}

}
