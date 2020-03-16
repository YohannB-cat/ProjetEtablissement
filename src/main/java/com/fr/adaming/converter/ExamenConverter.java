package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.entity.Examen;

public class ExamenConverter implements IConverter<Examen, ExamenDto>{

	@Override
	public Examen entiteToDto(ExamenDto dto) {
		Examen entite = new Examen(0, dto.getDate(), dto.getType(), dto.getCoefficient());
		return entite;
	}

	@Override
	public List<Examen> listEntiteToDto(List<ExamenDto> dtoliste) {
		List<Examen> liste = new ArrayList<Examen>();
		for (ExamenDto dto : dtoliste) {
			liste.add(new Examen(0, dto.getDate(), dto.getType(), dto.getCoefficient()));
		}
		return liste;
	}

	@Override
	public ExamenDto dtoToEntite(Examen entite) {
		ExamenDto dto = new ExamenDto(entite.getDate(), entite.getType(),entite.getCoefficient());
		return dto;
	}

	@Override
	public List<ExamenDto> listDtoToEntite(List<Examen> entite) {
		List<ExamenDto> liste = new ArrayList<ExamenDto>();
		for (Examen e : entite) {
			liste.add(new ExamenDto(e.getDate(), e.getType(),e.getCoefficient()));
		}
		return liste;
	}
	


}
