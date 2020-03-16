package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.entity.Classe;

public class ClasseConverter implements IConverter<Classe, ClasseDto> {

	@Override
	public Classe EntiteToDto(ClasseDto dto) {
		Classe classe = new Classe(0, dto.getNom(), dto.getListe());
		return classe;
	}

	@Override
	public List<Classe> ListEntiteToDto(List<ClasseDto> dtoliste) {
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseDto dto : dtoliste) {
			liste.add(new Classe(0, dto.getNom(), dto.getListe()));
		}
		return liste;
	}

	@Override
	public ClasseDto DtoToEntite(Classe entite) {
		ClasseDto dto = new ClasseDto(entite.getNom(), entite.getEtudiants());
		return dto;
	}

	@Override
	public List<ClasseDto> ListDtoToEntite(List<Classe> entite) {
		List<ClasseDto> liste = new ArrayList<ClasseDto>();
		for (Classe c : entite) {
			liste.add(new ClasseDto(c.getNom(), c.getEtudiants()));
		}
		return liste;
	}
	
}
