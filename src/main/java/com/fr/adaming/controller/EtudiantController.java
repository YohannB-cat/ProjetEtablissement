package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@RestController
@RequestMapping(path = "/etudiant")
public class EtudiantController implements IEtudiantController {

	@Autowired
	@Qualifier("serviceetudiant")
	private IServiceEtudiant service;

	// Methode create
	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody EtudiantDto dto){
		// Conversion du dto en etu, enregistrement, reconversion en dto, stockage dans etu
		EtudiantDto etu = 
		EtudiantConverter.convertEtudiantToEtudiantDto()
		service.create(EtudiantConverter.convertEtudiantDtoToEtudiant(dto)));
		
		//initialisation de la reponse
		ResponseDto resp = null;
		
		//Attribution de la réponse en fonction du retour DB et de la conposition de l'objet
		if (etu != null) {
			resp = new ResponseDto(false, "SUCCESS", etu);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", etu);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

	@Override
	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody EtudiantDtoCreate dto) {
		
	}

	@Override
	@GetMapping(path = "/{id}")
	public void findById(@PathVariable int id) {

	}
	
	@Override
	@GetMapping(path = "/{name}")
	public void findByName(@PathVariable String name) {

	}

	@Override
	@GetMapping
	public void findAll() {

	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable int id) {

	}

}
