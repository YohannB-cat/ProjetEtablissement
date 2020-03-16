package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.entity.Absence;

public class AbsenceConverter implements IConverter<Absence, AbsenceDto> {

	@Override
	public Absence dtoToEntite(AbsenceDto dto) {
		Absence entite = new Absence(0, dto.getDebut(), dto.getFin(), dto.getJustification(),dto.getDescription());
		return entite;
	}

	@Override
	public List<Absence> listDtoToEntite(List<AbsenceDto> dtoliste) {
		List<Absence> liste = new ArrayList<Absence>();
		for (AbsenceDto dto : dtoliste) {
			liste.add(new Absence(0, dto.getDebut(), dto.getFin(), dto.getJustification(),dto.getDescription()));
		}
		return liste;
	}

	@Override
	public AbsenceDto entiteToDto(Absence entite) {
		AbsenceDto dto = new AbsenceDto(entite.getDebut(), entite.getFin(), entite.getJustification(),entite.getDescription());
		return dto;
	}

	@Override
	public List<AbsenceDto> listEntiteToDto(List<Absence> entite) {
		List<AbsenceDto> liste = new ArrayList<AbsenceDto>();
		for (Absence e : entite) {
			liste.add(new AbsenceDto(e.getDebut(), e.getFin(), e.getJustification(),e.getDescription()));
		}
		return liste;
	}
	

}
