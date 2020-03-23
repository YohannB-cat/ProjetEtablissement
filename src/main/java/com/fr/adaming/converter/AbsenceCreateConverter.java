package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.entity.Absence;

import lombok.extern.slf4j.Slf4j;

/**
 * Interface IConverter responsable de la couche converter de l'entité Absence vers AbsenceDtoCreate
 * @author Isaline
 * @since 1.0.X
 *
 */
@Component
@Slf4j
public class AbsenceCreateConverter implements IConverter<Absence, AbsenceDtoCreate> {

	@Override
	public Absence dtoToEntite(AbsenceDtoCreate dto) {
		try {
			if (dto == null || dto.getDebut() == null) {
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
			log.error("ERROR convert dto to entite"+e.getMessage());
			return null;
		}
	}

	@Override
	public List<Absence> listDtoToEntite(List<AbsenceDtoCreate> dtoliste) {
		try {
			if (dtoliste.isEmpty()) {
				return new ArrayList<>();
			}
			List<Absence> liste = new ArrayList<>();
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
			log.error("ERROR convert list dto to entite"+e.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public AbsenceDtoCreate entiteToDto(Absence entite) {
		try {
			if (entite == null) {
				return null;
			}
			return new AbsenceDtoCreate(entite.getId(), entite.getDebut().toString(), entite.getFin().toString(),
					entite.getJustification(), entite.getDescription(), entite.getEtudiant());
		} catch (NullPointerException e) {
			log.error("ERROR entite to DTO"+e.getMessage());
			return null;
		}
	}

	@Override
	public List<AbsenceDtoCreate> listEntiteToDto(List<Absence> entite) {
		try {
			if (entite == null) {
				return new ArrayList<>();
			}
			List<AbsenceDtoCreate> liste = new ArrayList<>();
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
			log.error("ERROR liste entite to DTO"+e.getMessage());
			return new ArrayList<>();
		}

	}
}
