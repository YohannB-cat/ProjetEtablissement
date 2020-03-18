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
		Etudiant entite = new Etudiant(dto.getId(), dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(),
				dto.getEmail(), dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude());
		return entite;
	}

	@Override
	public List<Etudiant> listDtoToEntite(List<EtudiantDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Etudiant> liste = new ArrayList<Etudiant>();
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
		EtudiantDtoCreate dto = new EtudiantDtoCreate(entite.getId(),entite.getNom(), entite.getPrenom(), entite.getAdresse(),
				entite.getCodePostale(), entite.getVille(), entite.isSexe(), entite.getCni(), entite.getTelephone(),
				entite.getEmail(), entite.isEnEtude());
		return dto;
	}

	@Override
	public List<EtudiantDtoCreate> listEntiteToDto(List<Etudiant> entite) {
		if(entite==null) {
			return null;
		}
		List<EtudiantDtoCreate> liste = new ArrayList<EtudiantDtoCreate>();
		for (Etudiant e : entite) {
			liste.add(entiteToDto(e));
		}
		return liste;
	}

}
