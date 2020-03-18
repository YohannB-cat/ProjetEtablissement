package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.NiveauDto;
import com.fr.adaming.entity.Niveau;
@Component
public class NiveauConverter implements IConverter<Niveau, NiveauDto> {

	@Override
	public Niveau dtoToEntite(NiveauDto dto) {
		if(dto==null) {
			return null;
		}
		Niveau entite = new Niveau(dto.getClasses(),0 , dto.getNom());
		return entite;
	}

	@Override
	public List<Niveau> listDtoToEntite(List<NiveauDto> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Niveau> liste = new ArrayList<Niveau>();
		for (NiveauDto dto : dtoliste) {
			liste.add(new Niveau(dto.getClasses(),0 , dto.getNom()));
		}
		return liste;
	}

	@Override
	public NiveauDto entiteToDto(Niveau entite) {
		if(entite==null) {
			return null;
		}
		NiveauDto dto = new NiveauDto(entite.getClasses(), entite.getNom());
		return dto;
	}

	@Override
	public List<NiveauDto> listEntiteToDto(List<Niveau> entite) {
		if(entite==null) {
			return null;
		}
		List<NiveauDto> liste = new ArrayList<NiveauDto>();
		for (Niveau e : entite) {
			liste.add(new NiveauDto(e.getClasses(), e.getNom()));
		}
		return liste;
	}
	

}
