package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;


@RequestMapping(path = "note/")
public interface INoteController {
	//create note
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody NoteDtoCreate dto);
	
	//read note by id
	@GetMapping (path = "{id}")
	public ResponseEntity<ResponseDto> findById (@RequestParam (name = "id", required = false) int id);
	
	//read all
	@GetMapping (path = "all")
	public ResponseEntity<ResponseDto> findAll();
	
	//update
	@PutMapping
	public ResponseEntity<ResponseDto> update (@RequestBody NoteDtoCreate dto);
	
	// delete Note by ID
	@DeleteMapping(path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);
	
	// Afficher la liste des notes par etudiant
	@GetMapping(path = "etudiant")
	public ResponseEntity<ResponseDto> listByEtudiant(@RequestParam(name = "id") int id_etudiant);

	
	
}
