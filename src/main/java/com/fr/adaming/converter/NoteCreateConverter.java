package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.entity.Note;

public class NoteCreateConverter implements IConverter<Note, NoteDtoCreate> {
	

	@Override
	public Note dtoToEntite(NoteDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		Note entite = new Note(dto.getId(), dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen());
		return entite;
	}

	@Override
	public List<Note> listDtoToEntite(List<NoteDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Note> liste = new ArrayList<Note>();
		for (NoteDtoCreate dto : dtoliste) {
			liste.add(new Note(dto.getId(), dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen()));
		}
		return liste;
	}

	@Override
	public NoteDtoCreate entiteToDto(Note entite) {
		if(entite==null) {
			return null;
		}
		NoteDtoCreate dto = new NoteDtoCreate(entite.getId(), entite.getModule(), entite.getValeur(), entite.getEtudiant(), entite.getExamen());
		return dto;
	}

	@Override
	public List<NoteDtoCreate> listEntiteToDto(List<Note> entite) {
		if(entite==null) {
			return null;
		}
		List<NoteDtoCreate> liste = new ArrayList<NoteDtoCreate>();
		for (Note e : entite) {
			liste.add(new NoteDtoCreate(e.getId(),e.getModule(), e.getValeur(), e.getEtudiant(), e.getExamen()));
		}
		return liste;
	}
	

}
