package com.fr.adaming.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.service.IExamenService;
import com.fr.adaming.service.IModuleService;


@RequestMapping (path ="examen/")
public interface IExamenController {
	

	
	
	//create examen 
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid ExamenDtoCreate dto);
	
	//read examen 
	@GetMapping (path = "id")
	public  ResponseEntity<ResponseDto> findById(@RequestParam (name = "id", required = false) int id);
	
	//read all examen
	@GetMapping (path = "all")
	public  ResponseEntity<ResponseDto> findAll();
	
	//update examen
	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody @Valid ExamenDtoCreate dto);
	
	//delete examen
	@DeleteMapping (path ="{id}")
	public  ResponseEntity<ResponseDto> deleteById(@PathVariable(name = "id") int id);

}
