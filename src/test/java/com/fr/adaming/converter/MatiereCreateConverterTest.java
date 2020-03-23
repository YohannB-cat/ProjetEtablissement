package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.entity.Matiere;

/**
 * Test de converter create matière
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@SpringBootTest
public class MatiereCreateConverterTest {
	
	@Autowired
	public IConverter<Matiere, MatiereDtoCreate> convert;
	
	//TEST DTO TO ENTITE
	
		/**
		 * Conversion d'un dto vers une entite
		 * Condition valide
		 */
		@Test
		public void testDtoToEntiteValid_shouldReturnEntite() {
			MatiereDtoCreate dto = new MatiereDtoCreate("math");
			
			Matiere mat = convert.dtoToEntite(dto);

			assertThat(mat).hasFieldOrPropertyWithValue("nom", dto.getNom());


		}
		
		/**
		 * Conversion d'un dto vers une entite
		 * Si le dto n'a pas d'attribut, l'entite est null
		 */
		@Test
		public void testDtoToEntiteWithBlankValue_shouldReturnNull() {
			MatiereDtoCreate dto = new MatiereDtoCreate();
			
			Matiere mat = convert.dtoToEntite(dto);
			
			assertThat(mat).isNull();
			
		}

		/**
		 * Conversion d'un dto vers une entite
		 * Si le dto est null, l'entite est null
		 */
		@Test
		public void testDtoToEntiteNull_shouldReturnNull() {
			MatiereDtoCreate dto = null;
			Matiere mat = convert.dtoToEntite(dto);
			
			assertThat(mat).isNull();
		}
		
		//TEST LISTE DTO TO ENTITE
		
		/**
		 * Conversion d'une liste dto vers une liste d'entite
		 * Condition valide
		 */
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
		
		/**
		 * Conversion d'une liste dto vers une liste d'entite
		 * Si un element de la liste est vide, il n'est pas convertit
		 */
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
		
		/**
		 * Conversion d'une liste dto vers une liste d'entite
		 * Si la liste dto est null, la liste entite est vide
		 */
		@Test
		public void testListDtoToEntiteNull_shouldReturnNull() {
			List<MatiereDtoCreate> listeMatiereDto = null;
			List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
			
			assertThat(listeEntite).isEmpty();
		}
		
		//TEST  ENTITE TO DTO
		
		/**
		 * Conversion d'une entite vers un dto
		 * Condition valide
		 */
		@Test
		public void testEntiteToDtoValid_shouldReturnDto() {
			Matiere mat = new Matiere("math");
			MatiereDtoCreate dtoMAt = convert.entiteToDto(mat);
			
			assertThat(dtoMAt).hasFieldOrPropertyWithValue("nom", mat.getNom());
		}
		
		/**
		 * Conversion d'une entite vers un dto
		 * Si l'entite est sans attribut, le dto est null
		 */
		@Test
		public void testEntiteToDtoWithBlanckEntite_shouldReturnNull() {
			Matiere mat = new Matiere();
			
			MatiereDtoCreate dtoMat = convert.entiteToDto(mat);
			
			assertThat(dtoMat).isNull();
		}
		
		/**
		 * Conversion d'une entite vers un dto
		 * Si l'entite est null, le dto est null
		 */
		@Test
		public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
			Matiere mat = null;
			
			MatiereDtoCreate dtoMat = convert.entiteToDto(mat);
			
			assertThat(dtoMat).isNull();
		}
		
		//TEST LISTE ENTITE  TO DTO
		
		/**
		 * Conversion d'une liste d'entite vers dto
		 * Condition valide
		 */
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
		
		/**
		 * Conversion d'une liste d'entite vers dto
		 * Si un élement de la liste n'a pas d'attribut, il n'est pas convertit
		 */
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

		/**
		 * Conversion d'une liste d'entite vers dto
		 * Si la liste est null, la liste retournée est vide
		 */
		@Test
		public void testListEntiteToDtoWithNullList_shouldReturnNull() {
			List<Matiere> listeMat = null;
			List<MatiereDtoCreate> dtoLiist = convert.listEntiteToDto(listeMat);
			
			assertThat(dtoLiist).isEmpty();
			
		}

}
