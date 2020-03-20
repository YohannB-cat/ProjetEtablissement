package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.NiveauDtoCreate;
import com.fr.adaming.entity.Niveau;
@Component
public class NiveauCreateConverter implements IConverter<Niveau, NiveauDtoCreate> {

	@Override
	public Niveau dtoToEntite(NiveauDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		return new Niveau(dto.getClasses(),dto.getId() , dto.getNom());
	}

	@Override
	public List<Niveau> listDtoToEntite(List<NiveauDtoCreate> dtoliste) {
		List<Niveau> liste = new ArrayList<>();
		for (NiveauDtoCreate dto : dtoliste) {
			liste.add(new Niveau(dto.getClasses(),dto.getId() , dto.getNom()));
		}
		return liste;
	}

	@Override
	public NiveauDtoCreate entiteToDto(Niveau entite) {
		if(entite==null) {
			return null;
		}
		return new NiveauDtoCreate(entite.getClasses(),entite.getId(), entite.getNom());
	}

	@Override
	public List<NiveauDtoCreate> listEntiteToDto(List<Niveau> entite) {
		List<NiveauDtoCreate> liste = new ArrayList<>();
		for (Niveau e : entite) {
			liste.add(new NiveauDtoCreate(e.getClasses(),e.getId(), e.getNom()));
		}
		return liste;
	}
}
