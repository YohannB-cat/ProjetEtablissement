package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.entity.Classe;

public class ClasseCreateConverter implements IConverter<Classe, ClasseDtoCreate> {

	@Override
	public Classe entiteToDto(ClasseDtoCreate dto) {
		Classe classe = new Classe(dto.getId(), dto.getNom(), dto.getListe());
		return classe;
	}

	@Override
	public List<Classe> listEntiteToDto(List<ClasseDtoCreate> dtoliste) {
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseDtoCreate dto : dtoliste) {
			liste.add(new Classe(dto.getId(), dto.getNom(), dto.getListe()));
		}
		return liste;
	}

	@Override
	public ClasseDtoCreate dtoToEntite(Classe entite) {
		ClasseDtoCreate dto = new ClasseDtoCreate(entite.getId(),entite.getNom(), entite.getEtudiants());
		return dto;
	}

	@Override
	public List<ClasseDtoCreate> listDtoToEntite(List<Classe> entite) {
		List<ClasseDtoCreate> liste = new ArrayList<ClasseDtoCreate>();
		for (Classe c : entite) {
			liste.add(new ClasseDtoCreate(c.getId(), c.getNom(), c.getEtudiants()));
		}
		return liste;
	}
	
}
