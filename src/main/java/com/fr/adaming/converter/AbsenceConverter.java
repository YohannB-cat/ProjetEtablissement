package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.entity.Absence;
@Component
public class AbsenceConverter implements IConverter<Absence, AbsenceDto> {

	@Override
	public Absence dtoToEntite(AbsenceDto dto) {
		if(dto==null) {
			return null;
		}
		Absence entite = new Absence(0, LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()), dto.getJustification(),dto.getDescription(), dto.getEtudiant());
		return entite;
	}

	@Override
	public List<Absence> listDtoToEntite(List<AbsenceDto> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Absence> liste = new ArrayList<Absence>();
		for (AbsenceDto dto : dtoliste) {
			liste.add(new Absence(0, LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()), dto.getJustification(),dto.getDescription(), dto.getEtudiant()));
		}
		return liste;
	}

	@Override
	public AbsenceDto entiteToDto(Absence entite) {
		if(entite==null) {
			return null;
		}
		AbsenceDto dto = new AbsenceDto(entite.getDebut().toString(), entite.getFin().toString(), entite.getJustification(),entite.getDescription(), entite.getEtudiant());
		return dto;
	}

	@Override
	public List<AbsenceDto> listEntiteToDto(List<Absence> entite) {
		if(entite==null) {
			return null;
		}
		List<AbsenceDto> liste = new ArrayList<AbsenceDto>();
		for (Absence e : entite) {
			liste.add(new AbsenceDto(e.getDebut().toString(), e.getFin().toString(), e.getJustification(),e.getDescription(),e.getEtudiant()));
		}
		return liste;
	}
	

}
