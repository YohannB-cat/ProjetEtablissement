package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.entity.Examen;

public class ExamenConverter implements IConverter<Examen, ExamenDto>{

	@Override
	public Examen dtoToEntite(ExamenDto dto) {
		if(dto==null) {
			return null;
		}
		Examen entite = new Examen(0, dto.getDate(), dto.getType(), dto.getCoefficient());
		return entite;
	}

	@Override
	public List<Examen> listDtoToEntite(List<ExamenDto> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Examen> liste = new ArrayList<Examen>();
		for (ExamenDto dto : dtoliste) {
			liste.add(new Examen(0, dto.getDate(), dto.getType(), dto.getCoefficient()));
		}
		return liste;
	}

	@Override
	public ExamenDto entiteToDto(Examen entite) {
		if(entite==null) {
			return null;
		}
		ExamenDto dto = new ExamenDto(entite.getDate(), entite.getType(),entite.getCoefficient());
		return dto;
	}

	@Override
	public List<ExamenDto> listEntiteToDto(List<Examen> entite) {
		if(entite==null) {
			return null;
		}
		List<ExamenDto> liste = new ArrayList<ExamenDto>();
		for (Examen e : entite) {
			liste.add(new ExamenDto(e.getDate(), e.getType(),e.getCoefficient()));
		}
		return liste;
	}
	


}
