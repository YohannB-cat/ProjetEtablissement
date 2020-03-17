package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.entity.Absence;
@Component
public class AbsenceCreateConverter implements IConverter<Absence, AbsenceDtoCreate> {

	@Override
	public Absence dtoToEntite(AbsenceDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		Absence entite = new Absence(dto.getId(), LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()), dto.getJustification(),dto.getDescription(),dto.getEtudiant());
		return entite;
	}

	@Override
	public List<Absence> listDtoToEntite(List<AbsenceDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Absence> liste = new ArrayList<Absence>();
		for (AbsenceDtoCreate dto : dtoliste) {
			liste.add(new Absence(dto.getId(), LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()), dto.getJustification(),dto.getDescription(),dto.getEtudiant()));
		}
		return liste;
	}

	@Override
	public AbsenceDtoCreate entiteToDto(Absence entite) {
		if(entite==null) {
			return null;
		}
		AbsenceDtoCreate dto = new AbsenceDtoCreate(entite.getId(),entite.getDebut().toString(), entite.getFin().toString(), entite.getJustification(),entite.getDescription(), entite.getEtudiant());
		return dto;
	}

	@Override
	public List<AbsenceDtoCreate> listEntiteToDto(List<Absence> entite) {
		if(entite==null) {
			return null;
		}
		List<AbsenceDtoCreate> liste = new ArrayList<AbsenceDtoCreate>();
		for (Absence e : entite) {
			liste.add(new AbsenceDtoCreate(e.getId(),e.getDebut().toString(), e.getFin().toString(), e.getJustification(),e.getDescription(),e.getEtudiant()));
		}
		return liste;
	}
	

}
