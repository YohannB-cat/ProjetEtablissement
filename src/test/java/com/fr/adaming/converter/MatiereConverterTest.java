package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.entity.Matiere;

/**
 * Test converter simple matiere
 * 
 * @author IN-LY-004
 *
 */
@SpringBootTest
public class MatiereConverterTest {

	@Autowired
	public IConverter<Matiere, MatiereDto> convert;

	// TEST DTO TO ENTITE

	/**
	 * Conversion d'un dto avec vers une entite 
	 * Condition valide
	 */
	@Test
	public void testDtoToEntiteValid_shouldReturnEntite() {
		MatiereDto dto = new MatiereDto("math");

		Matiere mat = convert.dtoToEntite(dto);

		assertThat(mat).hasFieldOrPropertyWithValue("nom", dto.getNom());

	}

	/**
	 * Conversion d'un sans attribut vers une entite 
	 * Si le dto est sans attribut,
	 * l'entite retournée est null
	 */
	@Test
	public void testDtoToEntiteWithBlankValue_shouldReturnNull() {
		MatiereDto dto = new MatiereDto();

		Matiere mat = convert.dtoToEntite(dto);

		assertThat(mat).isNull();

	}

	/**
	 * Conversion d'un dto null vers une entite 
	 * Si le dto est null, l'entite
	 * retournée est null
	 */
	@Test
	public void testDtoToEntiteNull_shouldReturnNull() {
		MatiereDto dto = null;
		Matiere mat = convert.dtoToEntite(dto);

		assertThat(mat).isNull();
	}

	// TEST LISTE DTO TO ENTITE

	/**
	 * Conversion d'une liste dto vers une liste d'entite 
	 * Condition valide
	 */
	@Test
	public void testListDtoToEntiteValid_shouldReturnEntite() {
		List<MatiereDto> listeMatiereDto = new ArrayList<>();
		;
		MatiereDto dto1 = new MatiereDto("math");
		MatiereDto dto2 = new MatiereDto("français");
		listeMatiereDto.add(dto1);
		listeMatiereDto.add(dto2);

		List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);

		assertThat(listeEntite).hasSize(2);
		assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto1.getNom());
		assertThat(listeEntite.get(1)).hasFieldOrPropertyWithValue("nom", dto2.getNom());

	}

	/**
	 * Conversion d'une liste dto vers une liste d'entite 
	 * Si un element de la liste
	 * est vide, il n'est pas convertit
	 */
	@Test
	public void testListDtoToEntiteWithOneBlankDto_shouldReturnNotBlankListItem() {
		List<MatiereDto> listeMatiereDto = new ArrayList<>();
		;
		MatiereDto dto1 = new MatiereDto();
		MatiereDto dto2 = new MatiereDto("math");
		listeMatiereDto.add(dto1);
		listeMatiereDto.add(dto2);

		List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);

		assertThat(listeEntite.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
	}

	/**
	 * Conversion d'une liste dto vers une liste d'entite 
	 * Si la liste est null, la
	 * liste convertit est vide
	 */
	@Test
	public void testListDtoToEntiteNull_shouldReturnNull() {
		List<MatiereDto> listeMatiereDto = null;
		List<Matiere> listeEntite = convert.listDtoToEntite(listeMatiereDto);

		assertThat(listeEntite).isEmpty();
	}

	// TEST ENTITE TO DTO

	/**
	 * Conversion d'une entite vers dto Condition valide
	 */
	@Test
	public void testEntiteToDtoValid_shouldReturnDto() {
		Matiere mat = new Matiere("math");
		MatiereDto dtoMAt = convert.entiteToDto(mat);

		assertThat(dtoMAt).hasFieldOrPropertyWithValue("nom", mat.getNom());
	}

	/**
	 * Conversion d'une entite vers dto 
	 * Si l'entite est sans attribut, le dto est
	 * null
	 */
	@Test
	public void testEntiteToDtoWithBlanckEntite_shouldReturnNull() {
		Matiere mat = new Matiere();

		MatiereDto dtoMat = convert.entiteToDto(mat);

		assertThat(dtoMat).isNull();
	}

	/**
	 * Conversion d'une entite vers dto 
	 * Si l'entite est null, le dto est null
	 */
	@Test
	public void testEntiteToDtoWithNullEntite_shouldReturnNull() {
		Matiere mat = null;

		MatiereDto dtoMat = convert.entiteToDto(mat);

		assertThat(dtoMat).isNull();
	}

	// TEST LISTE ENTITE TO DTO

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

		List<MatiereDto> dtoListe = convert.listEntiteToDto(listeMat);

		assertThat(dtoListe).hasSize(listeMat.size());
		assertThat(dtoListe.get(0)).hasFieldOrPropertyWithValue("nom", mat1.getNom());
		assertThat(dtoListe.get(1)).hasFieldOrPropertyWithValue("nom", mat2.getNom());
	}

	/**
	 * Conversion d'une liste d'entite vers dto 
	 * Si un element de la liste est vide, il n'est pas convertit
	 */
	@Test
	public void testListEntiteToDtoWithBlankItem_shouldReturnListWithNoBlankItem() {
		List<Matiere> listeMat = new ArrayList<>();
		Matiere dto1 = new Matiere();
		Matiere dto2 = new Matiere("math");
		listeMat.add(dto1);
		listeMat.add(dto2);

		List<MatiereDto> dtoLiist = convert.listEntiteToDto(listeMat);

		assertThat(dtoLiist.get(0)).hasFieldOrPropertyWithValue("nom", dto2.getNom());
	}

	/**
	 * Conversion d'une liste d'entite vers dto 
	 * Si la liste est null, la liste retournée est vide
	 */
	@Test
	public void testListEntiteToDtoWithNullList_shouldReturnNull() {
		List<Matiere> listeMat = null;
		List<MatiereDto> dtoLiist = convert.listEntiteToDto(listeMat);

		assertThat(dtoLiist).isEmpty();

	}

}
