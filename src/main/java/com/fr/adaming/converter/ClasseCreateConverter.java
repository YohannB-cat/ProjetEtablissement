package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.entity.Classe;

public class ClasseCreateConverter implements IConverter<Classe, ClasseDtoCreate> {

	@Override
	public Classe dtoToEntite(ClasseDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		Classe classe = new Classe(dto.getId(), dto.getNom(), dto.getListe());
		return classe;
	}

	@Override
	public List<Classe> listDtoToEntite(List<ClasseDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseDtoCreate dto : dtoliste) {
			liste.add(new Classe(dto.getId(), dto.getNom(), dto.getListe()));
		}
		return liste;
	}

	@Override
	public ClasseDtoCreate entiteToDto(Classe entite) {
		if(entite==null) {
			return null;
		}
		ClasseDtoCreate dto = new ClasseDtoCreate(entite.getId(),entite.getNom(), entite.getEtudiants());
		return dto;
	}

	@Override
	public List<ClasseDtoCreate> listEntiteToDto(List<Classe> entite) {
		if(entite==null) {
			return null;
		}
		List<ClasseDtoCreate> liste = new ArrayList<ClasseDtoCreate>();
		for (Classe c : entite) {
			liste.add(new ClasseDtoCreate(c.getId(), c.getNom(), c.getEtudiants()));
		}
		return liste;
	}
	
}
