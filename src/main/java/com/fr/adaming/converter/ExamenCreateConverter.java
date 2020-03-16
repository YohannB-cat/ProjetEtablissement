package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.entity.Examen;

public class ExamenCreateConverter implements IConverter<Examen, ExamenDtoCreate>{

	@Override
	public Examen dtoToEntite(ExamenDtoCreate dto) {
		Examen entite = new Examen(dto.getId(), dto.getDate(), dto.getType(), dto.getCoefficient());
		return entite;
	}

	@Override
	public List<Examen>  listDtoToEntite(List<ExamenDtoCreate> dtoliste) {
		List<Examen> liste = new ArrayList<Examen>();
		for (ExamenDtoCreate dto : dtoliste) {
			liste.add(new Examen(dto.getId(), dto.getDate(), dto.getType(), dto.getCoefficient()));
		}
		return liste;
	}

	@Override
	public ExamenDtoCreate entiteToDto(Examen entite) {
		ExamenDtoCreate dto = new ExamenDtoCreate(entite.getId(), entite.getDate(), entite.getType(),entite.getCoefficient());
		return dto;
	}

	@Override
	public List<ExamenDtoCreate> listEntiteToDto(List<Examen> entite) {
		List<ExamenDtoCreate> liste = new ArrayList<ExamenDtoCreate>();
		for (Examen e : entite) {
			liste.add(new ExamenDtoCreate(e.getId(), e.getDate(), e.getType(),e.getCoefficient()));
		}
		return liste;
	}
	


}
