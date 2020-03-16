package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Note;

public interface INoteService {
	
	public Note create(Note note);

	public List<Note> findAll();

	public Note findById(int id);

	public Note findByEtudiant(int etudiant);

	public boolean update(Note note);

	public boolean deleteById(int id);
	
	public boolean deleteByEtudiant(int etudiant);

}
