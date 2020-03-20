package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.entity.Module;

@SpringBootTest
public class ModuleCreateConverterTest {

	@Autowired
	public IConverter<Module, ModuleDtoCreate> convert;
	
	
	// TEST DTO TO ENTITE

	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		ModuleDtoCreate dto = new ModuleDtoCreate("math");

		Module mod = convert.dtoToEntite(dto);

		assertThat(mod).hasFieldOrPropertyWithValue("nom", dto.getNom());

	}

	@Test
	public void testDtoToEntiteWithBlankValue_shouldReturnNull() {
		ModuleDtoCreate dto = new ModuleDtoCreate();

		Module mod = convert.dtoToEntite(dto);

		assertThat(mod).isNull();

	}

	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		ModuleDtoCreate dto = null;
		Module modul = convert.dtoToEntite(dto);

		assertThat(modul).isNull();
	}

	// TEST LISTE DTO TO ENTITE

	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<ModuleDtoCreate> listeModuleDto = new ArrayList<>();
		
		ModuleDtoCreate dto1 = new ModuleDtoCreate("math");
		ModuleDtoCreate dto2 = new ModuleDtoCreate("français");
		listeModuleDto.add(dto1);
		listeModuleDto.add(dto2);

		List<Module> listeEntite = convert.listDtoToEntite(listeModuleDto);

		assertThat(listeEntite).hasSize(2);
		assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto1.getNom());
		assertThat(listeEntite.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getNom());

	}

	@Test
	public void testListDtoToEntiteWithOneBlankDto_shouldReturnNotBlankListItem() {
		List<ModuleDtoCreate> listeModuleDto = new ArrayList<>();
		
		ModuleDtoCreate dto1 = new ModuleDtoCreate();
		ModuleDtoCreate dto2 = new ModuleDtoCreate("math");
		listeModuleDto.add(dto1);
		listeModuleDto.add(dto2);

		List<Module> listeEntite = convert.listDtoToEntite(listeModuleDto);

		assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
	}

	@Test
	public void testListDtoToEntiteNull_shouldReturnNull() {
		List<ModuleDtoCreate> listeModuleDto = null;
		List<Module> listeEntite = convert.listDtoToEntite(listeModuleDto);

		assertThat(listeEntite).isEmpty();
	}

	// TEST ENTITE TO DTO

	@Test
	public void testEntiteToDtoValid_shouldReturnDto() {
		Module mod = new Module(10,"math");
		ModuleDtoCreate dtoMod = convert.entiteToDto(mod);

		assertThat(dtoMod).hasFieldOrPropertyWithValue("nom", mod.getNom());
	}

	@Test
	public void testEntiteToDtoWithBlanckEntite_shouldReturnNull() {
		Module mod = new Module();

		ModuleDtoCreate dtoMat = convert.entiteToDto(mod);

		assertThat(dtoMat).isNull();
	}

	@Test
	public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
		Module mod = null;

		ModuleDtoCreate dtoMod = convert.entiteToDto(mod);

		assertThat(dtoMod).isNull();
	}

	// TEST LISTE ENTITE TO DTO

	@Test
	public void testListEntiteToDtoValid_shouldReturnEntite() {
		List<Module> listeMod = new ArrayList<Module>();
		Module mod1 = new Module(5,"math");
		Module mod2 = new Module(6,"français");
		listeMod.add(mod1);
		listeMod.add(mod2);

		List<ModuleDtoCreate> dtoListe = convert.listEntiteToDto(listeMod);

		assertThat(dtoListe).hasSize(listeMod.size());
		assertThat(dtoListe.get(0)).hasFieldOrPropertyWithValue("nom", mod1.getNom());
		assertThat(dtoListe.get(1)).hasFieldOrPropertyWithValue("nom", mod2.getNom());
	}

	@Test
	public void testListEntiteToDtoWithBlankItem_shouldReturnListWithNoBlankItem() {
		List<Module> listeMod = new ArrayList<>();
		Module dto1 = new Module();
		Module dto2 = new Module(6,"math");
		listeMod.add(dto1);
		listeMod.add(dto2);

		List<ModuleDtoCreate> dtoLiist = convert.listEntiteToDto(listeMod);

		assertThat(dtoLiist.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
	}

	@Test
	public void testListEntiteToDtoWithNullList_shouldReturnNull() {
		List<Module> listeMod = null;
		List<ModuleDtoCreate> dtoLiist = convert.listEntiteToDto(listeMod);

		assertThat(dtoLiist).isEmpty();

	}

}
