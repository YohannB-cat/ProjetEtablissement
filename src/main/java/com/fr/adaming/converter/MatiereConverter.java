package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.entity.Matiere;

public class MatiereConverter implements IConverter<Matiere, MatiereDto> {

	@Override
	public Matiere entiteToDto(MatiereDto dto) {
		Matiere entite = new Matiere(0, dto.getNom());
		return entite;
	}

	@Override
	public List<Matiere> listEntiteToDto(List<MatiereDto> dtoliste) {
		List<Matiere> liste = new ArrayList<Matiere>();
		for (MatiereDto dto : dtoliste) {
			liste.add(new Matiere(0, dto.getNom()));
		}
		return liste;
	}

	@Override
	public MatiereDto dtoToEntite(Matiere entite) {
		MatiereDto dto = new MatiereDto(entite.getNom());
		return dto;
	}

	@Override
	public List<MatiereDto> listDtoToEntite(List<Matiere> entite) {
		List<MatiereDto> liste = new ArrayList<MatiereDto>();
		for (Matiere e : entite) {
			liste.add(new MatiereDto(e.getNom()));
		}
		return liste;
	}
	

}
