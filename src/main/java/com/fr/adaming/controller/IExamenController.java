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
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;


@RequestMapping (path ="examen/")
public interface IExamenController {
	

	
	
	//create examen 
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid ExamenDtoCreate dto);
	
	//read examen 
	@GetMapping (path = "{id}")
	public  ResponseEntity<ResponseDto> findById(@PathVariable (name = "id", required = false) Integer id);
	
	//read all examen
	@GetMapping (path = "all")
	public  ResponseEntity<ResponseDto> findAll();
	
	//update examen
	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody @Valid ExamenDtoCreate dto);
	
	//delete examen
	@DeleteMapping (path ="{id}")
	public  ResponseEntity<ResponseDto> deleteById(@PathVariable(name = "id") int id);
	
	// Afficher la liste des examens par matiere
	@GetMapping(path = "matiere")
	public ResponseEntity<ResponseDto> listByMatiere(@RequestParam(name = "id") int idMatiere);

}
