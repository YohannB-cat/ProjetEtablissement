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
		return new Etudiant(0, dto.getNom(), dto.getPrenom(), dto.getAdresse(), dto.getVille(),
				dto.getEmail(), dto.getCodePostal(), dto.getCni(), dto.getTelephone(), dto.isSexe(), dto.isEnEtude());
		
	}

	@Override
	public List<Etudiant> listDtoToEntite(List<EtudiantDto> dtoliste) {
		if(dtoliste==null) {
			return new ArrayList<>();
		}
		List<Etudiant> liste = new ArrayList<>();
		for (EtudiantDto dto : dtoliste) {
			liste.add(dtoToEntite(dto));
		}
		return liste;
	}

	@Override
	public EtudiantDto entiteToDto(Etudiant entite) {
		if(entite==null) {
			return null;
		}

		return new EtudiantDto(entite.getNom(), entite.getPrenom(), entite.getAdresse(),
				entite.getCodePostale(), entite.getVille(), entite.isSexe(), entite.getCni(), entite.getTelephone(),
				entite.getEmail(), entite.isEnEtude());
	}

	@Override
	public List<EtudiantDto> listEntiteToDto(List<Etudiant> entite) {
		if(entite==null) {
			return new ArrayList<>();
		}
		List<EtudiantDto> liste = new ArrayList<>();
		for (Etudiant e : entite) {
			liste.add(entiteToDto(e));
		}
		return liste;
	}

}
