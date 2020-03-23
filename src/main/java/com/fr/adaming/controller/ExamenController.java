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
import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.service.IExamenService;

@RestController
public class ExamenController implements IExamenController {
	

	@Autowired
	@Qualifier("examenservice")
	private IExamenService service;
	
	@Autowired
	private IConverter<Examen, ExamenDtoCreate> examCreateDto;
	
	@Autowired
	private IConverter<Examen, ExamenDto> examDto;
	

	// create
	@Override
	public ResponseEntity<ResponseDto> create(@Valid ExamenDtoCreate dto) {
		ExamenDtoCreate exam =
				examCreateDto.entiteToDto(service.create(examCreateDto.dtoToEntite(dto)));
		
				//initialisation de la reponse
				ResponseDto resp = null;
				
				//Attribution de la réponse en fonction du retour DB et de la conposition de l'objet
				if (exam != null) {
					resp = new ResponseDto(false, WebConstant.SUCCESS, exam);
					return ResponseEntity.status(HttpStatus.OK).body(resp);
				}
				resp = new ResponseDto(true, WebConstant.FAIL, exam);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// find
	@Override
	public ResponseEntity<ResponseDto> findById(Integer id) {
		ExamenDto exam = examDto.entiteToDto(service.findById(id));
		//initialisation de la reponse
		ResponseDto resp = null;
		
		if (exam != null) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, exam);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, WebConstant.FAIL, exam);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
		
	}

	// find All
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<ExamenDto> list = examDto.listEntiteToDto(service.findAll());
		ResponseDto resp = new ResponseDto(false, WebConstant.SUCCESS, list);
		return ResponseEntity.status(HttpStatus.OK).body(resp);
		
	}

	// update
	@Override
	public ResponseEntity<ResponseDto> update(@Valid ExamenDtoCreate dto) {
		boolean result = service.update(examCreateDto.dtoToEntite(dto));
				
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false,WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete by Id
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;
		
		if (result) {
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@Override
	public ResponseEntity<ResponseDto> listByMatiere(int idMatiere){
		ResponseDto resp = null;
		List<Examen> list = service.listByMatiere(idMatiere);
		if (!list.isEmpty()) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

}
