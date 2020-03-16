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
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.INiveauService;

@RestController
public class NiveauController implements INiveauController {

	@Autowired
	@Qualifier("niveauservice")
	private INiveauService service;

	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody NiveauDto dto) {
		NiveauDto etu = 
				NiveauConverter.convertNiveauToNiveauDto().
			service.create(NiveauConverter.convertNiveauDtoToNiveau(dto)));
				
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
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody NiveauDtoCreate dto) {
		boolean result = service.update(NiveauConverter.convertNiveauDtoCreateToNiveau(dto));
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
	public ResponseEntity<ResponseDto> findById(@PathVariable int id) {
		NiveauDtoCreate dto = NiveauConverter.convertNiveauToNiveauDtoCreate(service.findById(id));
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
	public ResponseEntity<ResponseDto> findByName(@PathVariable String name) {
		NiveauDtoCreate dto = NiveauConverter.convertNiveauToNiveauDtoCreate(service.findByName(name));
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
		List<EtudiantDtoCreate> list = service.findAll();

		return ResponseDto resp = new ResponseDto(false, "SUCCESS", list);		
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
		boolean result = service.delete(id);
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
}
