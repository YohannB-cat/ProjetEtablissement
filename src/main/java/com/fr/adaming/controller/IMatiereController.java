package com.fr.adaming.controller;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@RequestMapping(path = "matiere/")
public interface IMatiereController {
	
	
	// create matiere
	@PostMapping (path="create")
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid MatiereDtoCreate dto);

	// read matiere by id
	@GetMapping (path = "{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable (name = "id", required = false) Integer id);
	
	// read all
	@GetMapping (path = "all")
	public ResponseEntity<ResponseDto> findAll();
	
	//find liste matiere by module
	@GetMapping (path = "bymodule/{matieres}")
	public ResponseEntity<ResponseDto> findMatiereByMatieres (int matieres);
	
	// update matiere by id
	@PutMapping (path="update")
	public ResponseEntity<ResponseDto> update(@RequestBody MatiereDtoCreate dto);
	
	// delete matiere by id
	@DeleteMapping (path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);
	

}
