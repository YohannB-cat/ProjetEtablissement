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
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.dto.ResponseDto;


@RequestMapping(path = "/classe")
public interface IClasseController {

	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody ClasseDtoCreate dto);

	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody ClasseDtoCreate dto);

	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable int id);

	@GetMapping
	public ResponseEntity<ResponseDto> findAll();

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable int id);

}
