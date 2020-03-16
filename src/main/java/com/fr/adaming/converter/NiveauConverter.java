package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.NiveauDto;
import com.fr.adaming.entity.Niveau;

public class NiveauConverter implements IConverter<Niveau, NiveauDto> {

	@Override
	public Niveau EntiteToDto(NiveauDto dto) {
		Niveau entite = new Niveau(dto.getClasses(),0 , dto.getNom());
		return entite;
	}

	@Override
	public List<Niveau> ListEntiteToDto(List<NiveauDto> dtoliste) {
		List<Niveau> liste = new ArrayList<Niveau>();
		for (NiveauDto dto : dtoliste) {
			liste.add(new Niveau(dto.getClasses(),0 , dto.getNom()));
		}
		return liste;
	}

	@Override
	public NiveauDto DtoToEntite(Niveau entite) {
		NiveauDto dto = new NiveauDto(entite.getClasses(), entite.getNom());
		return dto;
	}

	@Override
	public List<NiveauDto> ListDtoToEntite(List<Niveau> entite) {
		List<NiveauDto> liste = new ArrayList<NiveauDto>();
		for (Niveau e : entite) {
			liste.add(new NiveauDto(e.getClasses(), e.getNom()));
		}
		return liste;
	}
	

}
