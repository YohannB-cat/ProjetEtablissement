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

import com.fr.adaming.converter.ClasseConverter;
import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IClasseService;

public class ClassController implements IClasseController {
	
	@Autowired
	@Qualifier("classeservice")
	private IClasseService service;

	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody ClasseDto dto) {
		ClasseDto etu = 
				ClasseConverter.convertClasseToClasseDto().
				service.create(ClasseConverter.convertClasseDtoToClasse(dto)));
			
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
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody ClasseDtoCreate dto) {
		boolean result = service.update(ClasseConverter.convertClasseDtoCreateToClasse(dto));
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
		ClasseDtoCreate dto = ClasseConverter.convertClasseToClasseDtoCreate(service.findById(id));
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
		ClasseDtoCreate dto = ClasseConverter.convertClasseToClasseDtoCreate(service.findByName(name));
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
		List<ClasseDtoCreate> list = service.findAll();

		return ResponseDto resp = new ResponseDto(false, "SUCCESS", list);
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(int id) {
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
