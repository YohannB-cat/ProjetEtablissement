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

import com.fr.adaming.config.WebConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.service.IClasseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/classe")
public class ClasseController implements IClasseController {
	
	@Autowired
	@Qualifier("classeservice")
	private IClasseService service;
	
	@Autowired
	private IConverter<Classe, ClasseDto> convert;
	@Autowired
	private IConverter<Classe, ClasseDtoCreate> convertCreate;

	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody ClasseDtoCreate dto) {
		ClasseDtoCreate etu = 
				convertCreate.entiteToDto(
				service.create(convertCreate.dtoToEntite(dto)));
			
		ResponseDto resp = null;
		
		if (etu != null) {
			log.info("ClassCreate OK");
			resp = new ResponseDto(false, WebConstant.SUCCESS, etu);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		else {
			log.warn("ClassCreate FAIL");
			resp = new ResponseDto(true, WebConstant.FAIL, etu);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
		
	}

	@Override
	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody ClasseDtoCreate dto) {
		boolean result = service.update(convertCreate.dtoToEntite(dto));
		ResponseDto resp = null;

		if (result) {
			log.info("ClassUpdate OK");
			resp = new ResponseDto(false, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("ClassUpdate FAIL");
		resp = new ResponseDto(true, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id) {
		ClasseDto dto = convert.entiteToDto(service.findById(id));
		ResponseDto resp = null;

		if (dto != null) {
			log.info("ClassFinById OK");
			resp = new ResponseDto(false, WebConstant.SUCCESS, dto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("ClassFinById FAIL");
		resp = new ResponseDto(true, WebConstant.FAIL, dto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> findAll() {
		List<ClasseDto> list = convert.listEntiteToDto(service.findAll());
		
		log.info("ClassFindAll OK");
		ResponseDto resp = new ResponseDto(false, WebConstant.SUCCESS, list);
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			log.info("ClassDelete OK");
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("ClassDelete FAIL");
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
}
