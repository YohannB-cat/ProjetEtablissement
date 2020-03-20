package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.config.WebConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.IMatiereService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MatiereController implements IMatiereController {

	@Autowired
	@Qualifier("matiereservice")
	private IMatiereService service;

	@Autowired
	private IConverter<Matiere, MatiereDtoCreate> matiereCreateDto;

	@Autowired
	private IConverter<Matiere, MatiereDto> matiereDto;

	// create matiere
	@Override
	public ResponseEntity<ResponseDto> create(@Valid MatiereDtoCreate dto) {
		MatiereDtoCreate matiere1 = matiereCreateDto.entiteToDto(service.create(matiereCreateDto.dtoToEntite(dto)));

		// initialisation de la reponse
		ResponseDto resp = null;

		// Attribution de la réponse en fonction du retour DB et de la conposition de
		// l'objet
		if (matiere1 != null) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, matiere1);
			log.info("SUCCESS create matiere");
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, WebConstant.FAIL, matiere1);
		log.warn("FAIL create matiere");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// find by id
	@Override
	public ResponseEntity<ResponseDto> findById(Integer id) {
		MatiereDto matiere2 = matiereDto.entiteToDto(service.findById(id));

		// initialisation de la reponse
		ResponseDto resp = null;

		if (matiere2 != null) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, matiere2);
			log.info("SUCCESS find by id matiere");
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, WebConstant.FAIL, matiere2);
			log.warn("FAIL find by id matiere");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}

	}

	// find all
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<MatiereDto> list = matiereDto.listEntiteToDto(service.findAll());

		ResponseDto resp = null;
		resp = new ResponseDto(false, WebConstant.SUCCESS, list);
		log.info("SUCCESS find All matiere");
		return ResponseEntity.status(HttpStatus.OK).body(resp);

	}

	// find matiere by module
	@Override
	public ResponseEntity<ResponseDto> findMatiereByMatieres(int matieres) {
		List<MatiereDto> listMatiere = matiereDto.listEntiteToDto(service.findMatiereByIdModule(matieres));

		ResponseDto resp = null;

		resp = new ResponseDto(false, WebConstant.SUCCESS, listMatiere);
		log.info("SUCCESS find matiere by matieres");
		return ResponseEntity.status(HttpStatus.OK).body(resp);

	}

	// udpdate
	@Override
	public ResponseEntity<ResponseDto> update(MatiereDtoCreate dto) {
		Boolean isSuccess = service.update(matiereCreateDto.dtoToEntite(dto));

		ResponseDto resp = null;

		if (isSuccess) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, isSuccess);
			log.info("SUCCESS update matiere");
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, WebConstant.FAIL, null);
		log.warn("FAIL update matiere");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete By ID
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result1 = service.deleteById(id);
		ResponseDto resp = null;

		if (result1) {
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			log.info("SUCCESS delete by id");
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		log.warn("FAIL delete by Id");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

}
