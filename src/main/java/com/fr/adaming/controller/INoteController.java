package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.ResponseDto;


@RequestMapping(path = "note/")
public interface INoteController {

	//create note
	
	//read note by id
	
	//read note by etudiant
	
	//read all
	
	// Update Note by ID

	// Update Note by etudiant

	// delete Note by ID
	@DeleteMapping(path = "{id}")
	public ResponseEntity<ResponseDto> deleteNoteById(@PathVariable(name = "id") int id);

	
	// delete Note by etudiant
	
	
}
