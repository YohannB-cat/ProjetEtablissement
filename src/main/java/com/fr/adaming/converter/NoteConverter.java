package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.entity.Note;

public class NoteConverter implements IConverter<Note, NoteDto> {
	

	@Override
	public Note dtoToEntite(NoteDto dto) {
		if(dto==null) {
			return null;
		}
		Note entite = new Note(0, dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen());
		return entite;
	}

	@Override
	public List<Note> listDtoToEntite(List<NoteDto> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Note> liste = new ArrayList<Note>();
		for (NoteDto dto : dtoliste) {
			liste.add(new Note(0, dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen()));
		}
		return liste;
	}

	@Override
	public NoteDto entiteToDto(Note entite) {
		if(entite==null) {
			return null;
		}
		NoteDto dto = new NoteDto(entite.getModule(), entite.getValeur(), entite.getEtudiant(), entite.getExamen());
		return dto;
	}

	@Override
	public List<NoteDto> listEntiteToDto(List<Note> entite) {
		if(entite==null) {
			return null;
		}
		List<NoteDto> liste = new ArrayList<NoteDto>();
		for (Note e : entite) {
			liste.add(new NoteDto(e.getModule(), e.getValeur(), e.getEtudiant(), e.getExamen()));
		}
		return liste;
	}
	

}
