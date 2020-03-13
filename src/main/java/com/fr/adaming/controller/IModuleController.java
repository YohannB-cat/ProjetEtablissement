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

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.dto.ResponseDto;

@RequestMapping (path = "module/")
public interface IModuleController {
	
	//create module
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid ModuleDtoCreate dto);
	
	//read module by id
	@GetMapping (path = "id")
	public ResponseEntity<ResponseDto> findById (@RequestParam (name = "id", required = false) int id);
	
	//read module by nom
	@GetMapping (path = "nom")
	public ResponseEntity<ResponseDto> findByNom (@RequestParam (name = "nom", required = false) String nom);
	
	//read all
	@GetMapping (path = "all")
	public  ResponseEntity<ResponseDto> findAll();
	
	//update module
	@PutMapping
	public ResponseEntity<ResponseDto> update (@RequestBody @Valid ModuleDto dto);
		
	//delete module by id
	@DeleteMapping (path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);
	
	//delete module by nom
	@DeleteMapping (path = "{nom}")
	public ResponseEntity<ResponseDto> deleteByNom (@PathVariable(name = "nom") String nom);
	

}
