package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseDto;

public class ClasseConverter implements IConverter<Classe, ClasseDto> {

	@Override
	public Classe EntiteToDto(ClasseDto dto) {
		Classe classe = new Classe();
		return classe;
	}

	@Override
	public List<Classe> ListEntiteToDto(List<ClasseDto> dtoliste) {
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseDto dto : dtoliste) {
			liste.add(new Classe(dto;
		}
		return liste;
	}

	@Override
	public ClasseDto DtoToEntite(Classe entite) {
		ClasseDto dto = new ClasseDto();
		return dto;
	}

	@Override
	public List<ClasseDto> ListDtoToEntite(List<Classe> entite) {
		List<ClasseDto> liste = new ArrayList<ClasseDto>();
		for (Classe c : entite) {
			liste.add(new ClasseDto(c.get) ;
		}
		return liste;
	}
	
}
