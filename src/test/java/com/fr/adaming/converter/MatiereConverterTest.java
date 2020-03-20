package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereConverterTest {
	
	@Autowired
	public IConverter<Matiere, MatiereDto> convert;
	

	//TEST DTO TO ENTITE
	
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		MatiereDto dto = new MatiereDto("math");
		
		Matiere mat = convert.dtoToEntite(dto);

		assertThat(mat).hasFieldOrPropertyWithValue("nom", dto.getNom());


	}
	
	@Test
	public void testDtoToEntiteWithBlankValue_shouldReturnNull() {
		MatiereDto dto = new MatiereDto();
		
		Matiere mat = convert.dtoToEntite(dto);
		
		assertThat(mat).isNull();
		
	}

	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		MatiereDto dto = null;
		Matiere mat = convert.dtoToEntite(dto);
		
		assertThat(mat).isNull();
	}
	
	//TEST LISTE DTO TO ENTITE
	
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<MatiereDto> listeMatiereDto = new ArrayList();;
		MatiereDto dto1 = new MatiereDto("math");
		MatiereDto dto2 = new MatiereDto("français");
		listeMatiereDto.add(dto1);
		listeMatiereDto.add(dto2);
		
		List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
		
		assertThat(listeEntite).hasSize(2);
		assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto1.getNom());
		assertThat(listeEntite.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
		
	}
	
	@Test
	public void testListDtoToEntiteWithOneBlankDto_shouldReturnNotBlankListItem() {
		List<MatiereDto> listeMatiereDto = new ArrayList();;
		MatiereDto dto1 = new MatiereDto();
		MatiereDto dto2 = new MatiereDto("math");
		listeMatiereDto.add(dto1);
		listeMatiereDto.add(dto2);
		
		List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
		
		assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
	}
	
	@Test
	public void testListDtoToEntiteNull_shouldReturnNull() {
		List<MatiereDto> listeMatiereDto = null;
		List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);
		
		assertThat(listeEntite).isEmpty();
	}
	
	//TEST  ENTITE TO DTO
	
	@Test
	public void testEntiteToDtoValid_shouldReturnDto() {
		Matiere mat = new Matiere("math");
		MatiereDto dtoMAt = convert.entiteToDto(mat);
		
		assertThat(dtoMAt).hasFieldOrPropertyWithValue("nom", mat.getNom());
	}
	
	@Test
	public void testEntiteToDtoWithBlanckEntite_shouldReturnNull() {
		Matiere mat = new Matiere();
		
		MatiereDto dtoMat = convert.entiteToDto(mat);
		
		assertThat(dtoMat).isNull();
	}
	
	@Test
	public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
		Matiere mat = null;
		
		MatiereDto dtoMat = convert.entiteToDto(mat);
		
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
		
		List<MatiereDto> dtoListe = convert.listEntiteToDto(listeMat);
		
		assertThat(dtoListe).hasSize(listeMat.size());
		assertThat(dtoListe.get(0)).hasFieldOrPropertyWithValue("nom", mat1.getNom());
		assertThat(dtoListe.get(1)).hasFieldOrPropertyWithValue("nom", mat2.getNom());
	}
	
	@Test
	public void testListEntiteToDtoWithBlankItem_shouldReturnListWithNoBlankItem() {
		List<Matiere> listeMat = new ArrayList();
		Matiere dto1 = new Matiere();
		Matiere dto2 = new Matiere("math");
		listeMat.add(dto1);
		listeMat.add(dto2);
		
		List<MatiereDto> dtoLiist = convert.listEntiteToDto(listeMat);
		
		assertThat(dtoLiist.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
	}

	@Test
	public void testListEntiteToDtoWithNullList_shouldReturnNull() {
		List<Matiere> listeMat = null;
		List<MatiereDto> dtoLiist = convert.listEntiteToDto(listeMat);
		
		assertThat(dtoLiist).isEmpty();
		
	}
	
	
}
