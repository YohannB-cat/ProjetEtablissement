package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.entity.Note;

public class NoteConverter implements IConverter<Note, NoteDto> {
	

	@Override
	public Note entiteToDto(NoteDto dto) {
		Note entite = new Note(0, dto.getModule(),dto.getValeur(), dto.getEtudiant());
		return entite;
	}

	@Override
	public List<Note> listEntiteToDto(List<NoteDto> dtoliste) {
		List<Note> liste = new ArrayList<Note>();
		for (NoteDto dto : dtoliste) {
			liste.add(new Note(0, dto.getModule(),dto.getValeur(), dto.getEtudiant()));
		}
		return liste;
	}

	@Override
	public NoteDto dtoToEntite(Note entite) {
		NoteDto dto = new NoteDto(entite.getIdEtudiant(), entite.getId_module(),entite.getValeur());
		return dto;
	}

	@Override
	public List<NoteDto> listDtoToEntite(List<Note> entite) {
		List<NoteDto> liste = new ArrayList<NoteDto>();
		for (Note e : entite) {
			liste.add(new NoteDto(e.getIdEtudiant(), e.getId_module(),e.getValeur()));
		}
		return liste;
	}
	

}
