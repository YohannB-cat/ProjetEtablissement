package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.config.WebConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.dto.NiveauDto;
import com.fr.adaming.dto.NiveauDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;
import com.fr.adaming.service.INiveauService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/niveau")
public class NiveauController implements INiveauController {

	@Autowired
	@Qualifier("niveauservice")
	private INiveauService service;
	
	@Autowired
	private IConverter<Niveau, NiveauDto> convert;
	
	@Autowired
	private IConverter<Classe, ClasseDto> convertClasse;
	
	@Autowired
	private IConverter<Niveau, NiveauDtoCreate> convertCreate;

	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create( NiveauDto dto) {
		NiveauDtoCreate etu = 
			convertCreate.entiteToDto(
			service.create(convert.dtoToEntite(dto)));
				
		ResponseDto resp = null;
				
		if (etu != null) {
			log.info("NiveauCreate OK");
			resp = new ResponseDto(false, WebConstant.SUCCESS, etu);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("NiveauCreate FAIL");
		resp = new ResponseDto(true, WebConstant.FAIL, etu);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@PutMapping
	public ResponseEntity<ResponseDto> update( NiveauDtoCreate dto) {
		boolean result = service.update(convertCreate.dtoToEntite(dto));
		ResponseDto resp = null;

		if (result) {
			log.info("NiveauUpdate OK");
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("NiveauUpdate FAIL");
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> findById(Integer id) {
		NiveauDto dto = convert.entiteToDto(service.findById(id));
		ResponseDto resp = null;

		if (dto != null) {
			log.info("NiveauFindById OK");
			resp = new ResponseDto(false, WebConstant.SUCCESS, dto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("NiveauFindById FAIL");
		resp = new ResponseDto(true, WebConstant.FAIL, dto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}


	@Override
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> findAll() {
		List<NiveauDto> list = convert.listEntiteToDto(service.findAll());

		log.info("NiveauFindAll OK");
		ResponseDto resp = new ResponseDto(false, WebConstant.SUCCESS, list);
		return ResponseEntity.status(HttpStatus.OK).body(resp);		
	}

	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete( int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			log.info("NiveauDelete OK");
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		log.warn("NiveauDelete FAIL");
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@Override
	@GetMapping(path = "/classe/{idNiveau}")
	public ResponseEntity<ResponseDto> findClasseByNiveau(@PathVariable(name = "idNiveau") int id) {
		List<ClasseDto> list = convertClasse.listEntiteToDto(service.findListClasseByIdNiveau(id));
		if(list != null && !list.isEmpty()) {

			log.info("NiveauFindClasseByNiveau OK");
			ResponseDto resp = new ResponseDto(false, WebConstant.SUCCESS, list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}else {
			log.warn("NiveauFindClasseByNiveau FAIL");
			ResponseDto resp = new ResponseDto(true, WebConstant.FAIL, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}		
	}
}
