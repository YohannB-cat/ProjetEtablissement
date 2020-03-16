package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.entity.Note;

public class NoteCreateConverter implements IConverter<Note, NoteDtoCreate> {
	

	@Override
	public Note entiteToDto(NoteDtoCreate dto) {
		Note entite = new Note(dto.getId(), dto.getModule(),dto.getValeur(), dto.getEtudiant());
		return entite;
	}

	@Override
	public List<Note> listEntiteToDto(List<NoteDtoCreate> dtoliste) {
		List<Note> liste = new ArrayList<Note>();
		for (NoteDtoCreate dto : dtoliste) {
			liste.add(new Note(dto.getId(), dto.getModule(),dto.getValeur(), dto.getEtudiant()));
		}
		return liste;
	}

	@Override
	public NoteDtoCreate dtoToEntite(Note entite) {
		NoteDtoCreate dto = new NoteDtoCreate(entite.getId(),entite.getIdEtudiant(), entite.getId_module(),entite.getValeur());
		return dto;
	}

	@Override
	public List<NoteDtoCreate> listDtoToEntite(List<Note> entite) {
		List<NoteDtoCreate> liste = new ArrayList<NoteDtoCreate>();
		for (Note e : entite) {
			liste.add(new NoteDtoCreate(e.getId(),e.getIdEtudiant(), e.getId_module(),e.getValeur()));
		}
		return liste;
	}
	

}
