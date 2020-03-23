package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.entity.Classe;
@Component
public class ClasseCreateConverter implements IConverter<Classe, ClasseDtoCreate> {

	@Override
	public Classe dtoToEntite(ClasseDtoCreate dto) {
		if(dto==null || dto.getId() == 0) {
			return null;
		}
		return new Classe(dto.getId(), dto.getNom(), dto.getListe());
	}

	@Override
	public List<Classe> listDtoToEntite(List<ClasseDtoCreate> dtoliste) {
		List<Classe> liste = new ArrayList<>();
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
		return new ClasseDtoCreate(entite.getId(),entite.getNom(), entite.getEtudiants());
	}

	@Override
	public List<ClasseDtoCreate> listEntiteToDto(List<Classe> entite) {
		List<ClasseDtoCreate> liste = new ArrayList<>();
		for (Classe c : entite) {
			liste.add(new ClasseDtoCreate(c.getId(), c.getNom(), c.getEtudiants()));
		}
		return liste;
	}
	
}
