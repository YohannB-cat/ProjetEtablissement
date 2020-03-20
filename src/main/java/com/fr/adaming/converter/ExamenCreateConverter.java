package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.entity.Examen;
@Component
public class ExamenCreateConverter implements IConverter<Examen, ExamenDtoCreate>{

	@Override
	public Examen dtoToEntite(ExamenDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		return new Examen(dto.getId(), dto.getDate(), dto.getType(), dto.getCoefficient());
	}

	@Override
	public List<Examen>  listDtoToEntite(List<ExamenDtoCreate> dtoliste) {
		List<Examen> liste = new ArrayList<>();
		if(dtoliste!=null) {
			for (ExamenDtoCreate dto : dtoliste) {
				liste.add(new Examen(dto.getId(), dto.getDate(), dto.getType(), dto.getCoefficient()));
			}
		}
		return liste;
	}

	@Override
	public ExamenDtoCreate entiteToDto(Examen entite) {
		if(entite==null) {
			return null;
		}
		return new ExamenDtoCreate(entite.getId(), entite.getDate(), entite.getType(),entite.getCoefficient());
	}

	@Override
	public List<ExamenDtoCreate> listEntiteToDto(List<Examen> entite) {
		List<ExamenDtoCreate> liste = new ArrayList<>();
		if(entite!=null) {
			for (Examen e : entite) {
				liste.add(new ExamenDtoCreate(e.getId(), e.getDate(), e.getType(),e.getCoefficient()));
			}
		}
		return liste;
	}
	


}
