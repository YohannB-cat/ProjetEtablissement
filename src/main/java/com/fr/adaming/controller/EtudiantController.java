package com.fr.adaming.controller;

import java.util.List;

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

import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.service.IEtudiantService;

@RestController
@RequestMapping(path = "/etudiant")
public class EtudiantController implements IEtudiantController {

	@Autowired
	@Qualifier("etudiantservice")
	private IEtudiantService service;
	
	private IConverter<Etudiant, EtudiantDto> convert;
	private IConverter<Etudiant, EtudiantDtoCreate> convertCreate;

	// Methode create
	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody EtudiantDtoCreate dto){
		EtudiantDtoCreate etu = 
		convertCreate.entiteToDto(
		service.create(convertCreate.dtoToEntite(dto)));
		
		ResponseDto resp = null;
		
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
		boolean result = service.update(convertCreate.dtoToEntite(dto));
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id) {
		EtudiantDto dto = convert.entiteToDto(service.findById(id));
		ResponseDto resp = null;

		if (dto != null) {
			resp = new ResponseDto(false, "SUCCESS", dto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", dto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@GetMapping(path = "/{name}")
	public ResponseEntity<ResponseDto> findByName(@PathVariable(name = "name") String name) {
		EtudiantDto dto = convert.entiteToDto(service.findByNom(name));
		ResponseDto resp = null;

		if (dto != null) {
			resp = new ResponseDto(false, "SUCCESS", dto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", dto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@GetMapping
	public ResponseEntity<ResponseDto> findAll() {
		List<EtudiantDto> list =	convert.listEntiteToDto(service.findAll());
		
		ResponseDto resp = new ResponseDto(false, "SUCCESS", list);
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

}
