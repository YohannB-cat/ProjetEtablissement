package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.entity.Matiere;
@Component
public class MatiereCreateConverter implements IConverter<Matiere, MatiereDtoCreate> {

	@Override
	public Matiere dtoToEntite(MatiereDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		Matiere entite = new Matiere(dto.getId(), dto.getNom());
		return entite;
	}

	@Override
	public List<Matiere> listDtoToEntite(List<MatiereDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Matiere> liste = new ArrayList<Matiere>();
		for (MatiereDtoCreate dto : dtoliste) {
			liste.add(new Matiere(dto.getId(), dto.getNom()));
		}
		return liste;
	}

	@Override
	public MatiereDtoCreate entiteToDto(Matiere entite) {
		if(entite==null) {
			return null;
		}
		MatiereDtoCreate dto = new MatiereDtoCreate(entite.getId(), entite.getNom());
		return dto;
	}

	@Override
	public List<MatiereDtoCreate> listEntiteToDto(List<Matiere> entite) {
		if(entite==null) {
			return null;
		}
		List<MatiereDtoCreate> liste = new ArrayList<MatiereDtoCreate>();
		for (Matiere e : entite) {
			liste.add(new MatiereDtoCreate(e.getId(), e.getNom()));
		}
		return liste;
	}
	

}
