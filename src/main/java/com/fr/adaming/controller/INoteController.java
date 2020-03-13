package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.Dto.ResponseDto;

@RequestMapping(path = "note/")
public interface INoteController {

	// Update Note by ID

	// Update Note by etudiant

	// delete Note by ID
	@DeleteMapping(path = "note/delete/{id}")
	public ResponseEntity<ResponseDto> deleteNoteById(@PathVariable(name = "id") int id);

	// delete Note by etudiant

}
