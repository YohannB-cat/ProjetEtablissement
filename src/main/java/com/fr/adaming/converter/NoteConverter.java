package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.entity.Note;
@Component
public class NoteConverter implements IConverter<Note, NoteDto> {
	

	@Override
	public Note dtoToEntite(NoteDto dto) {
		if(dto==null) {
			return null;
		}
		return new Note(0, dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen());
	}

	@Override
	public List<Note> listDtoToEntite(List<NoteDto> dtoliste) {
		List<Note> liste = new ArrayList<>();
		if(dtoliste!=null) {
			for (NoteDto dto : dtoliste) {
				liste.add(new Note(0, dto.getModule(),dto.getValeur(),dto.getEtudiant(),dto.getExamen()));
			}
		}
		return liste;
	}

	@Override
	public NoteDto entiteToDto(Note entite) {
		if(entite==null) {
			return null;
		}
		return new NoteDto(entite.getModule(), entite.getValeur(), entite.getEtudiant(), entite.getExamen());
	}

	@Override
	public List<NoteDto> listEntiteToDto(List<Note> entite) {
		List<NoteDto> liste = new ArrayList<>();
		if(entite!=null) {
			for (Note e : entite) {
				liste.add(new NoteDto(e.getModule(), e.getValeur(), e.getEtudiant(), e.getExamen()));
			}
		}
		return liste;
	}
	

}
