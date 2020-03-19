package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.converter.ModuleConverter;
import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Module;
import com.fr.adaming.service.IModuleService;

@RestController
public class ModuleController implements IModuleController {

	@Autowired
	@Qualifier("moduleservice")
	private IModuleService service;

	@Autowired
	private IConverter<Module, ModuleDtoCreate> convertCreateDto;

	@Autowired
	private IConverter<Module, ModuleDto> convertDto;

	// create
	@Override
	public ResponseEntity<ResponseDto> create(ModuleDtoCreate dto) {
		ModuleDtoCreate module = convertCreateDto.entiteToDto(service.create(convertCreateDto.dtoToEntite(dto)));

		// initialisation de la reponse
		ResponseDto resp = null;

		// Attribution de la réponse en fonction du retour DB et de la conposition de
		// l'objet
		if (module != null) {
			resp = new ResponseDto(false, "SUCCESS", module);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", module);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// find By Id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		ModuleDto module = convertDto.entiteToDto(service.findById(id));

		// initialisation de la reponse
		ResponseDto resp = null;

		if (module != null) {
			resp = new ResponseDto(false, "SUCCESS", module);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", module);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	// Find All
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<ModuleDto> list = convertDto.listEntiteToDto(service.findAll());

		ResponseDto resp = null;
		if (list != null) {
			resp = new ResponseDto(false, "SUCCESS", list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// Update
	@Override
	public ResponseEntity<ResponseDto> update(ModuleDtoCreate dto) {
		boolean result = service.update(convertCreateDto.dtoToEntite(dto));
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", result);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// Delete By Id
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(false, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

}
