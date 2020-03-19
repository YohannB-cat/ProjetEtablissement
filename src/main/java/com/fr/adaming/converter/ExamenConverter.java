package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.entity.Examen;
@Component
public class ExamenConverter implements IConverter<Examen, ExamenDto>{

	@Override
	public Examen dtoToEntite(ExamenDto dto) {
		if(dto==null) {
			return null;
		}
		return new Examen(0, dto.getDate(), dto.getType(), dto.getCoefficient());
	}

	@Override
	public List<Examen> listDtoToEntite(List<ExamenDto> dtoliste) {
		if(dtoliste==null) {
			return  new ArrayList<>();
		}
		List<Examen> liste = new ArrayList<>();
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
		return new ExamenDto(entite.getDate(), entite.getType(),entite.getCoefficient());
	}

	@Override
	public List<ExamenDto> listEntiteToDto(List<Examen> entite) {
		if(entite==null) {
			return new ArrayList<>();
		}
		List<ExamenDto> liste = new ArrayList<>();
		for (Examen e : entite) {
			liste.add(new ExamenDto(e.getDate(), e.getType(),e.getCoefficient()));
		}
		return liste;
	}
	


}
