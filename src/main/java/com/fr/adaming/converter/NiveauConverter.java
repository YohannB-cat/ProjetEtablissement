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
		return new Niveau(dto.getClasses(),0 , dto.getNom());
	}

	@Override
	public List<Niveau> listDtoToEntite(List<NiveauDto> dtoliste) {
		List<Niveau> liste = new ArrayList<>();
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
		return new NiveauDto(entite.getClasses(), entite.getNom());
	}

	@Override
	public List<NiveauDto> listEntiteToDto(List<Niveau> entite) {
		List<NiveauDto> liste = new ArrayList<>();
		for (Niveau e : entite) {
			liste.add(new NiveauDto(e.getClasses(), e.getNom()));
		}
		return liste;
	}
}
