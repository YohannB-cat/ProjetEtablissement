package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.entity.Etudiant;

public class EtudiantCreateConverter implements IConverter<Etudiant, EtudiantDtoCreate> {

	@Override
	public Etudiant EntiteToDto(EtudiantDtoCreate dto) {
		Etudiant entite = new Etudiant(dto.getId(), dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(),
				dto.getEmail(), dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude());
		return entite;
	}

	@Override
	public List<Etudiant> ListEntiteToDto(List<EtudiantDtoCreate> dtoliste) {
		List<Etudiant> liste = new ArrayList<Etudiant>();
		for (EtudiantDtoCreate dto : dtoliste) {
			liste.add(new Etudiant(dto.getId(), dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(), dto.getEmail(),
					dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude()));
		}
		return liste;
	}

	@Override
	public EtudiantDtoCreate DtoToEntite(Etudiant entite) {
		EtudiantDtoCreate dto = new EtudiantDtoCreate(entite.getId(),entite.getNom(), entite.getPrenom(), entite.getAdresse(),
				entite.getCodePostale(), entite.getVille(), entite.isSexe(), entite.getCni(), entite.getTelephone(),
				entite.getEmail(), entite.isEnEtude());
		return dto;
	}

	@Override
	public List<EtudiantDtoCreate> ListDtoToEntite(List<Etudiant> entite) {
		List<EtudiantDtoCreate> liste = new ArrayList<EtudiantDtoCreate>();
		for (Etudiant e : entite) {
			liste.add(new EtudiantDtoCreate(e.getId(),e.getNom(), e.getPrenom(), e.getAdresse(), e.getCodePostale(), e.getVille(),
					e.isSexe(), e.getCni(), e.getTelephone(), e.getEmail(), e.isEnEtude()));
		}
		return liste;
	}

}
