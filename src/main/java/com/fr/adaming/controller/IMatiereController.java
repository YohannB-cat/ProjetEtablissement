package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@RequestMapping(path = "matiere/")
public interface IMatiereController {

	
	// create matiere
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody MatiereDtoCreate dto);

	// read matiere by id

	// read matiere by nom

	// read all

	// update matiere by id

	// update matiere by nom

	// delete matiere by id
	
	//delete matiere by nom

}
