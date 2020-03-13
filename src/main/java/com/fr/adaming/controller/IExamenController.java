package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping (path ="examen/")
public interface IExamenController {
	
	//create examen 
	@PostMapping
	public void ResponseEntity<ResponseDto> createExamen(@RequestBody @Valid ExamenCreateDto dto);
	
	//read examen 
	@GetMapping
	public void ResponseEntity<ResponseDto> readExamen(@RequestBody @Valid ExamenReadDto dto);
	
	//read all examen
	@GetMapping
	public void ResponseEntity<ResponseDto> readAll(@RequestBody @Valid ExamenReadAllDto dto);
	
	//update examen
	@PutMapping
	public void ResponseEntity<ResponseDto> update(@RequestBody @Valid UpdateDto dto);
	
	//delete examen
	@DeleteMapping (path = "{id}")
	public void ResponseEntity<ResponseDto> delete(@PathVariable (name = "id") int id);

}
