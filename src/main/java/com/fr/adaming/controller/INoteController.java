package com.fr.adaming.controller;

import java.util.List;

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

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;


@RequestMapping(path = "note/")
public interface INoteController {

	//create note
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid NoteDtoCreate dto);
	
	//read note by id
	@GetMapping (path = "id")
	public ResponseEntity<ResponseDto> findById (@RequestParam (name = "id", required = false) int id);
	
	//read note by etudiant
	@GetMapping (path = "etudiant")
	public ResponseEntity<ResponseDto> findByEtudiant (@RequestParam (name = "etudiant", required = false) int etudiant);
	
	//read all
	@GetMapping (path = "all")
	public ResponseEntity<ResponseDto> findAll();
	
	//update
	@PutMapping
	public ResponseEntity<ResponseDto> update (@RequestBody @Valid NoteDto dto);
	
	// delete Note by ID
	@DeleteMapping(path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);

	
	// delete Note by etudiant
	@DeleteMapping(path = "{etudiant}")
	public ResponseEntity<ResponseDto> deleteByEtudiant (@PathVariable(name = "etudiant") int etudiant);
	
}
