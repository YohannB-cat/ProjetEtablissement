package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;



public interface INoteController {
	//create note
	public ResponseEntity<ResponseDto> create(NoteDtoCreate dto);
	
	//read note by id
	public ResponseEntity<ResponseDto> findById (int id);
	
	//read all
	public ResponseEntity<ResponseDto> findAll();
	
	//update
	public ResponseEntity<ResponseDto> update ( NoteDtoCreate dto);
	
	// delete Note by ID
	public ResponseEntity<ResponseDto> deleteById (int id);
	
	// Afficher la liste des notes par etudiant
	public ResponseEntity<ResponseDto> listByEtudiant(int idetudiant);

	
	
}
