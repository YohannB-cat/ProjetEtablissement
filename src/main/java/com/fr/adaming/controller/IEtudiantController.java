package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;


public interface IEtudiantController {

	
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody EtudiantDtoCreate dto);

	
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody EtudiantDtoCreate dto);

	
	public ResponseEntity<ResponseDto> findById(@PathVariable int id);

	
	public ResponseEntity<ResponseDto> findAll();

	
	public ResponseEntity<ResponseDto> delete(@PathVariable int id);
}
