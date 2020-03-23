package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.entity.Classe;

@Component
public class ClasseConverter implements IConverter<Classe, ClasseDto> {

	@Override
	public Classe dtoToEntite(ClasseDto dto) {
		if (dto == null) {
			return null;
		}
		return new Classe(0, dto.getNom(), dto.getListe());
	}

	@Override
	public List<Classe> listDtoToEntite(List<ClasseDto> dtoliste) {
		List<Classe> liste = new ArrayList<>();
		for (ClasseDto dto : dtoliste) {
			liste.add(new Classe(0, dto.getNom(), dto.getListe()));
		}
		return liste;
	}

	@Override
	public ClasseDto entiteToDto(Classe entite) {
		if (entite == null) {
			return null;
		}
		return new ClasseDto(entite.getNom(), entite.getEtudiants());
	}

	@Override
	public List<ClasseDto> listEntiteToDto(List<Classe> entite) {
		List<ClasseDto> liste = new ArrayList<>();
		if (entite == null) {
			return liste;
		}
		for (Classe c : entite) {
			if (c.getNom() != null || c.getEtudiants() != null) {
				liste.add(new ClasseDto(c.getNom(), c.getEtudiants()));
			}
		}
		return liste;
	}

}
