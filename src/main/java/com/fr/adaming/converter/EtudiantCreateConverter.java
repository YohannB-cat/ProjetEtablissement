package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.entity.Etudiant;
@Component
public class EtudiantCreateConverter implements IConverter<Etudiant, EtudiantDtoCreate> {

	@Override
	public Etudiant dtoToEntite(EtudiantDtoCreate dto) {
		if(dto==null) {
			return null;
		}
	return new Etudiant(dto.getId(), dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(),
				dto.getEmail(), dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude());

	}

	@Override
	public List<Etudiant> listDtoToEntite(List<EtudiantDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return new ArrayList<>();
		}
		List<Etudiant> liste = new ArrayList<>();
		for (EtudiantDtoCreate dto : dtoliste) {
			liste.add(dtoToEntite(dto));
		}
		return liste;
	}

	@Override
	public EtudiantDtoCreate entiteToDto(Etudiant entite) {
		if(entite==null) {
			return null;
		}

		return new EtudiantDtoCreate(entite.getId(),entite.getNom(), entite.getPrenom(), entite.getAdresse(),
				entite.getCodePostale(), entite.getVille(), entite.isSexe(), entite.getCni(), entite.getTelephone(),
				entite.getEmail(), entite.isEnEtude());
	}

	@Override
	public List<EtudiantDtoCreate> listEntiteToDto(List<Etudiant> entite) {
		if(entite==null) {
			return new ArrayList<>();
		}
		List<EtudiantDtoCreate> liste = new ArrayList<>();
		for (Etudiant e : entite) {
			liste.add(entiteToDto(e));
		}
		return liste;
	}

}
