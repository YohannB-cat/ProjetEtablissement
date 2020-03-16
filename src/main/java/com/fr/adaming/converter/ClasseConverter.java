package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.entity.Classe;

public class ClasseConverter implements IConverter<Classe, ClasseDto> {

	@Override
	public Classe dtoToEntite(ClasseDto dto) {
		if(dto==null) {
			return null;
		}
		Classe classe = new Classe(0, dto.getNom(), dto.getListe());
		return classe;
	}

	@Override
	public List<Classe> listDtoToEntite(List<ClasseDto> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseDto dto : dtoliste) {
			liste.add(new Classe(0, dto.getNom(), dto.getListe()));
		}
		return liste;
	}

	@Override
	public ClasseDto entiteToDto(Classe entite) {
		if(entite==null) {
			return null;
		}
		ClasseDto dto = new ClasseDto(entite.getNom(), entite.getEtudiants());
		return dto;
	}

	@Override
	public List<ClasseDto> listEntiteToDto(List<Classe> entite) {
		if(entite==null) {
			return null;
		}
		List<ClasseDto> liste = new ArrayList<ClasseDto>();
		for (Classe c : entite) {
			liste.add(new ClasseDto(c.getNom(), c.getEtudiants()));
		}
		return liste;
	}
	
}
