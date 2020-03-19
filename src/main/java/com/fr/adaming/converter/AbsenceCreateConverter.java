package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.entity.Absence;

@Component
@SuppressWarnings("squid:S1148")
public class AbsenceCreateConverter implements IConverter<Absence, AbsenceDtoCreate> {

	@Override
	public Absence dtoToEntite(AbsenceDtoCreate dto) {
		try {
			if (dto == null) {
				return null;
			} else {
				Absence entite;
				if (dto.getEtudiant() == null) {
					entite = new Absence(dto.getId(), LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()),
							dto.getJustification(), dto.getDescription());
					entite.setEtudiant(null);
				} else {
					entite = new Absence(dto.getId(), LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()),
							dto.getJustification(), dto.getDescription(), dto.getEtudiant());
				}
				return entite;
			}
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public List<Absence> listDtoToEntite(List<AbsenceDtoCreate> dtoliste) {
		try {
			if (dtoliste == null) {
				return null;
			}
			List<Absence> liste = new ArrayList<Absence>();
			for (AbsenceDtoCreate dto : dtoliste) {
				if (dto.getEtudiant() != null) {
					liste.add(new Absence(LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()),
							dto.getJustification(), dto.getDescription(), dto.getEtudiant()));
				} else {
					Absence etuNull = new Absence(LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()),
							dto.getJustification(), dto.getDescription());
					etuNull.setEtudiant(null);
					liste.add(etuNull);
				}
			}
			return liste;
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public AbsenceDtoCreate entiteToDto(Absence entite) {
		try {
			if (entite == null) {
				return null;
			}
			AbsenceDtoCreate dto = new AbsenceDtoCreate(entite.getId(), entite.getDebut().toString(),
					entite.getFin().toString(), entite.getJustification(), entite.getDescription(),
					entite.getEtudiant());
			return dto;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<AbsenceDtoCreate> listEntiteToDto(List<Absence> entite) {
		try {
			if (entite == null) {
				List<AbsenceDtoCreate> listeNulle = new ArrayList<AbsenceDtoCreate>();
				return listeNulle;
			}
			List<AbsenceDtoCreate> liste = new ArrayList<AbsenceDtoCreate>();
			for (Absence e : entite) {
				if (e.getEtudiant() != null) {
					liste.add(new AbsenceDtoCreate(e.getId(), e.getDebut().toString(), e.getFin().toString(),
							e.getJustification(), e.getDescription(), e.getEtudiant()));
				} else {
					AbsenceDtoCreate dto = new AbsenceDtoCreate(e.getId(), e.getDebut().toString(),
							e.getFin().toString(), e.getJustification(), e.getDescription());
					dto.setEtudiant(null);
					liste.add(dto);
				}
			}
			return liste;
		} catch (NullPointerException e) {
			return null;
		}

	}
}
