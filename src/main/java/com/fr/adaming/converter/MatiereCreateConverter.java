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
		if (dto != null && dto.getNom() != null) {
			return new Matiere(dto.getId(), dto.getNom());

		} else {
			return null;
		}

	}

	@Override
	public List<Matiere> listDtoToEntite(List<MatiereDtoCreate> dtoliste) {
		List<Matiere> liste = new ArrayList<>();
		if (dtoliste == null) {
			return liste;
		}

		for (MatiereDtoCreate dto : dtoliste) {
			if (dto.getNom() != null) {
				liste.add(new Matiere(dto.getId(), dto.getNom()));
			}

		}
		return liste;
	}

	@Override
	public MatiereDtoCreate entiteToDto(Matiere entite) {
		if (entite != null && entite.getNom() != null) {
			return new MatiereDtoCreate(entite.getId(), entite.getNom());

		} else {
			return null;
		}
	}

	@Override
	public List<MatiereDtoCreate> listEntiteToDto(List<Matiere> entite) {
		List<MatiereDtoCreate> liste = new ArrayList<>();
		if (entite == null) {
			return liste;
		}
		for (Matiere e : entite) {
			if (e.getNom() != null) {
				liste.add(new MatiereDtoCreate(e.getId(), e.getNom()));
			}
		}
		return liste;
	}
}
