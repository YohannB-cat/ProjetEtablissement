package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping(path = "/absances")
public interface IAbsanceController {
	
	@Autowired
	@Qualifier("absancesservice")
	private IAbsanceService service;

	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody AbsenceDto dto);

	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody AbsenceDtoCreate dto);

	@GetMapping(path = "/{id}")
	public void findById(@PathVariable int id);

	@GetMapping
	public void findAll();

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable int id);

}
