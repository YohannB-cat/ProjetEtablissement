package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.entity.Module;

/**
 * test converter simple module
 * @author IN-LY-004
 *
 */
@SpringBootTest
public class ModuleConverterTest {
	
	@Autowired
	public IConverter<Module, ModuleDto> convert;
	
	//TEST DTO TO ENTITE
	
		/**
		 * Conversion dto vers entite
		 * Condition valide
		 */
		@Test
		public void testDtoToEntiteValid_shouldReturnEntite() {
			ModuleDto dto = new ModuleDto("sixieme");
			
			Module mod = convert.dtoToEntite(dto);

			assertThat(mod).hasFieldOrPropertyWithValue("nom", dto.getNom());

		}
		
		/**
		 * Conversion dto vers entite
		 * Si dto est sans attribut, le retour est null
		 */
		@Test
		public void testDtoToEntiteWithBlankValue_shouldReturnNull() {
			ModuleDto dto = new ModuleDto();
			
			Module mod = convert.dtoToEntite(dto);
			
			assertThat(mod).isNull();
			
		}

		/**
		 * Conversion dto vers entite
		 * Si dto est null, l'entite est null
		 */
		@Test
		public void testDtoToEntiteNull_shouldReturnNull() {
			ModuleDto dto = null;
			Module mod = convert.dtoToEntite(dto);
			
			assertThat(mod).isNull();
		}
		
		//TEST LISTE DTO TO ENTITE
		
		/**
		 * Conversion liste dto vers entite
		 * Condition valide
		 */
		@Test
		public void testListDtoToEntiteValid_shouldReturnEntite() {
			List<ModuleDto> listeModuleDto = new ArrayList<>();;
			ModuleDto dto1 = new ModuleDto("cinquieme");
			ModuleDto dto2 = new ModuleDto("troisieme");
			listeModuleDto.add(dto1);
			listeModuleDto.add(dto2);
			
			List<Module> listeEntite = convert.listDtoToEntite(listeModuleDto);
			
			assertThat(listeEntite).hasSize(2);
			assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto1.getNom());
			assertThat(listeEntite.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
			
		}
		
		/**
		 * Conversion liste dto vers entite
		 * Si un élement de la liste est vide, il n'est pas convertit
		 */
		@Test
		public void testListDtoToEntiteWithOneBlankDto_shouldReturnNotBlankListItem() {
			List<ModuleDto> listeModuleDto = new ArrayList<>();;
			ModuleDto dto1 = new ModuleDto();
			ModuleDto dto2 = new ModuleDto("math");
			listeModuleDto.add(dto1);
			listeModuleDto.add(dto2);
			
			List<Module> listeEntite = convert.listDtoToEntite(listeModuleDto);
			
			assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
		}
		
		/**
		 * Conversion liste dto vers entite
		 * Si la liste est null, on retourne est vide
		 */
		@Test
		public void testListDtoToEntiteNull_shouldReturnNull() {
			List<ModuleDto> listeMatiereDto = null;
			List<Module> listeEntite = convert.listDtoToEntite(listeMatiereDto);
			
			assertThat(listeEntite).isEmpty();
		}
		
		//TEST  ENTITE TO DTO
		
		/**
		 * Conversion  entite vers dto
		 * Condition valide
		 */
		@Test
		public void testEntiteToDtoValid_shouldReturnDto() {
			Module module = new Module("sixieme");
			ModuleDto dtoMod = convert.entiteToDto(module);
			
			assertThat(dtoMod).hasFieldOrPropertyWithValue("nom", module.getNom());
		}
		
		/**
		 * Conversion  entite vers dto
		 * Si l'entite est sans attribut, le dto est null
		 */
		@Test
		public void testEntiteToDtoWithBlanckEntite_shouldReturnNull() {
			Module modul = new Module();
			
			ModuleDto dtoMod = convert.entiteToDto(modul);
			
			assertThat(dtoMod).isNull();
		}
		
		/**
		 * Conversion  entite vers dto
		 * Si l'entite est null, le dto est null
		 */
		@Test
		public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
			Module mat = null;
			
			ModuleDto dtoMat = convert.entiteToDto(mat);
			
			assertThat(dtoMat).isNull();
		}
		
		//TEST LISTE ENTITE  TO DTO
		
		/**
		 * Conversion liste entite to dto
		 * Condition valide
		 */
		@Test
		public void testListEntiteToDtoValid_shouldReturnEntite() {
			List<Module> listeMod = new ArrayList<Module>();
			Module mat1 = new Module("math");
			Module mat2 = new Module("français");
			listeMod.add(mat1);
			listeMod.add(mat2);
			
			List<ModuleDto> dtoListe = convert.listEntiteToDto(listeMod);
			
			assertThat(dtoListe).hasSize(listeMod.size());
			assertThat(dtoListe.get(0)).hasFieldOrPropertyWithValue("nom", mat1.getNom());
			assertThat(dtoListe.get(1)).hasFieldOrPropertyWithValue("nom", mat2.getNom());
		}
		
		/**
		 * Conversion liste entite to dto
		 * Si un element de la liste est sans attribut, il n'est pas convertit
		 */
		@Test
		public void testListEntiteToDtoWithBlankItem_shouldReturnListWithNoBlankItem() {
			List<Module> listeMod = new ArrayList<>();
			Module dto1 = new Module();
			Module dto2 = new Module("math");
			listeMod.add(dto1);
			listeMod.add(dto2);
			
			List<ModuleDto> dtoLiist = convert.listEntiteToDto(listeMod);
			
			assertThat(dtoLiist.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
		}

		/**
		 * Conversion liste entite to dto
		 * Si la liste est null, on retourne une liste vide
		 */
		@Test
		public void testListEntiteToDtoWithNullList_shouldReturnNull() {
			List<Module> listeMod = null;
			List<ModuleDto> dtoLiist = convert.listEntiteToDto(listeMod);
			
			assertThat(dtoLiist).isEmpty();
			
		}

}
