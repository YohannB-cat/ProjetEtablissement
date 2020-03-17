package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.entity.Etudiant;
@Component
public class EtudiantConverter implements IConverter<Etudiant, EtudiantDto> {

	@Override
	public Etudiant dtoToEntite(EtudiantDto dto) {
		if(dto==null) {
			return null;
		}
		Etudiant entite = new Etudiant(0, dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(),
				dto.getEmail(), dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude());
		return entite;
	}

	@Override
	public List<Etudiant> listDtoToEntite(List<EtudiantDto> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Etudiant> liste = new ArrayList<Etudiant>();
		for (EtudiantDto dto : dtoliste) {
			liste.add(new Etudiant(0, dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(), dto.getEmail(),
					dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude()));
		}
		return liste;
	}

	@Override
	public EtudiantDto entiteToDto(Etudiant entite) {
		if(entite==null) {
			return null;
		}
		EtudiantDto dto = new EtudiantDto(entite.getNom(), entite.getPrenom(), entite.getAdresse(),
				entite.getCodePostale(), entite.getVille(), entite.isSexe(), entite.getCni(), entite.getTelephone(),
				entite.getEmail(), entite.isEnEtude());
		return dto;
	}

	@Override
	public List<EtudiantDto> listEntiteToDto(List<Etudiant> entite) {
		if(entite==null) {
			return null;
		}
		List<EtudiantDto> liste = new ArrayList<EtudiantDto>();
		for (Etudiant e : entite) {
			liste.add(new EtudiantDto(e.getNom(), e.getPrenom(), e.getAdresse(), e.getCodePostale(), e.getVille(),
					e.isSexe(), e.getCni(), e.getTelephone(), e.getEmail(), e.isEnEtude()));
		}
		return liste;
	}

}
