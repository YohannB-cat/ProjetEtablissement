package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.entity.Absence;

public class AbsenceCreateConverter implements IConverter<Absence, AbsenceDtoCreate> {

	@Override
	public Absence entiteToDto(AbsenceDtoCreate dto) {
		Absence entite = new Absence(dto.getId(), dto.getDebut(), dto.getFin(), dto.getJustification(),dto.getDescription());
		return entite;
	}

	@Override
	public List<Absence> listEntiteToDto(List<AbsenceDtoCreate> dtoliste) {
		List<Absence> liste = new ArrayList<Absence>();
		for (AbsenceDtoCreate dto : dtoliste) {
			liste.add(new Absence(dto.getId(), dto.getDebut(), dto.getFin(), dto.getJustification(),dto.getDescription()));
		}
		return liste;
	}

	@Override
	public AbsenceDtoCreate dtoToEntite(Absence entite) {
		AbsenceDtoCreate dto = new AbsenceDtoCreate(entite.getId(),entite.getDebut(), entite.getFin(), entite.getJustification(),entite.getDescription());
		return dto;
	}

	@Override
	public List<AbsenceDtoCreate> listDtoToEntite(List<Absence> entite) {
		List<AbsenceDtoCreate> liste = new ArrayList<AbsenceDtoCreate>();
		for (Absence e : entite) {
			liste.add(new AbsenceDtoCreate(e.getId(),e.getDebut(), e.getFin(), e.getJustification(),e.getDescription()));
		}
		return liste;
	}
	

}
