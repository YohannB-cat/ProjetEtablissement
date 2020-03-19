package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Note;

public interface INoteService {
	
	public Note create(Note note);

	public List<Note> findAll();

	public Note findById(int id);


	public boolean update(Note note);

	public boolean deleteById(int id);
	
	public List<Note> listByEtudiant(int idetudiant);


}
