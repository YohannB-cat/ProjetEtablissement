package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceDto;
import com.fr.adaming.entity.Absence;

@Component
public class AbsenceConverter implements IConverter<Absence, AbsenceDto> {

	@Override
	public Absence dtoToEntite(AbsenceDto dto) {
		try {
			if (dto == null) {
				return null;
			} else {
				Absence entite;
				if (dto.getEtudiant() == null) {
					entite = new Absence();
					entite.setDebut(LocalDate.parse(dto.getDebut()));
					entite.setFin(LocalDate.parse(dto.getFin()));
					entite.setJustification(dto.getJustification());
					entite.setDescription(dto.getDescription());
					entite.setEtudiant(null);
				} else {
					entite = new Absence(LocalDate.parse(dto.getDebut()), LocalDate.parse(dto.getFin()),
							dto.getJustification(), dto.getDescription(), dto.getEtudiant());
				}
				return entite;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Absence> listDtoToEntite(List<AbsenceDto> dtoliste) {
		try {
			if (dtoliste == null) {
				return null;
			}
			List<Absence> liste = new ArrayList<Absence>();
			for (AbsenceDto dto : dtoliste) {
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
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AbsenceDto entiteToDto(Absence entite) {
		try {
			if (entite == null) {
			return null;
		}
		AbsenceDto dto = new AbsenceDto(entite.getDebut().toString(), entite.getFin().toString(),
				entite.getJustification(), entite.getDescription(), entite.getEtudiant());
		return dto;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<AbsenceDto> listEntiteToDto(List<Absence> entite) {
		try {
			if (entite == null) {
				List<AbsenceDto> listeNulle = new ArrayList<AbsenceDto>();
				return listeNulle;
			}
			List<AbsenceDto> liste = new ArrayList<AbsenceDto>();
			for (Absence e : entite) {
				if (e.getEtudiant() != null) {
					liste.add(new AbsenceDto(e.getDebut().toString(), e.getFin().toString(), e.getJustification(),
							e.getDescription(), e.getEtudiant()));
				} else {
					AbsenceDto dto = new AbsenceDto();
					dto.setDebut(e.getDebut().toString());
					dto.setFin(e.getFin().toString());
					dto.setJustification(e.getJustification());
					dto.setDescription(e.getDescription());
					dto.setEtudiant(null);

					liste.add(dto);
				}
			}
			return liste;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}

	}
}