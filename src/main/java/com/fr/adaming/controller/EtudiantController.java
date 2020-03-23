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
import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.service.IEtudiantService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/etudiant")
public class EtudiantController implements IEtudiantController {

	@Autowired
	@Qualifier("etudiantservice")
	private IEtudiantService service;

	@Autowired
	private IConverter<Etudiant, EtudiantDto> convert;

	@Autowired
	private IConverter<Etudiant, EtudiantDtoCreate> convertCreate;

	private ResponseDto resp = new ResponseDto(false,null,null);

	// Methode create
	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody EtudiantDto dto) {
		EtudiantDtoCreate etu = convertCreate.entiteToDto(service.create(convert.dtoToEntite(dto)));
		this.resp.setObject(etu);
		
		if (etu != null) {
			setResponseSuccess();
			log.info("Creation etudiant Ok");
			return ResponseEntity.status(HttpStatus.OK).body(this.resp);
		}
		setResponseFail();
		log.warn("Creation etudiant fail");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.resp);

	}

	// update
	@Override
	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody EtudiantDtoCreate dto) {
		boolean result = service.update(convertCreate.dtoToEntite(dto));
		this.resp = new ResponseDto(false,null,null);

		if (result) {
			setResponseSuccess();
			log.info("Modification etudiant Ok");
			return ResponseEntity.status(HttpStatus.OK).body(this.resp);
		}
		setResponseFail();
		log.warn("Modification etudiant fail");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.resp);
	}

	// read
	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id) {
		EtudiantDto dto = convert.entiteToDto(service.findById(id));
		this.resp.setObject(dto);
		
		if (dto != null) {

			setResponseSuccess();
			log.info("Recherche etudiant par id Ok");
			return ResponseEntity.status(HttpStatus.OK).body(this.resp);
		}
		setResponseFail();
		log.warn("Recherche etudiant par id Fail");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.resp);
	}

	@Override
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> findAll() {
		List<EtudiantDto> list = convert.listEntiteToDto(service.findAll());
		this.resp.setObject(list);
		setResponseSuccess();
		log.info("Recherche etudiant all Ok");
		return ResponseEntity.status(HttpStatus.OK).body(this.resp);
	}

	// delete
	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id) {
		boolean result = service.deleteById(id);
		this.resp = new ResponseDto(false,null,null);

		if (result) {
			setResponseSuccess();
			log.info("Suppression etudiant par id Ok");
			return ResponseEntity.status(HttpStatus.OK).body(this.resp);
		}
		setResponseFail();
		log.warn("Suppression etudiant par id Fail");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.resp);
	}

	public void setResponseSuccess() {
		this.resp.setError(false);
		this.resp.setMessage(WebConstant.SUCCESS);
	}
	public void setResponseFail() {
		this.resp.setError(true);
		this.resp.setMessage(WebConstant.FAIL);
	}
	
	
}
