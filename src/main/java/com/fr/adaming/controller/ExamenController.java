package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.converter.ExamenConverter;
import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.service.IExamenService;

public class ExamenController implements IExamenController {

	@Autowired
	@Qualifier("serviceexamen")
	private IExamenService service;

	// create
	@Override
	public ResponseEntity<ResponseDto> create(@Valid ExamenDtoCreate dto) {
		ExamenDto exam = ExamenConverter.convertExamentToExamenDto().service.create(ExamenConverter.convertExamenDtoToExamen(dto)));
		
				//initialisation de la reponse
				ResponseDto resp = null;
				
				//Attribution de la réponse en fonction du retour DB et de la conposition de l'objet
				if (exam != null) {
					resp = new ResponseDto(false, "SUCCESS", exam);
					return ResponseEntity.status(HttpStatus.OK).body(resp);
				}
				resp = new ResponseDto(true, "FAIL", exam);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// find
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		Examen exam = service.findById(id);
		
		//initialisation de la reponse
		ResponseDto resp = null;
		
		if (exam != null) {
			resp = new ResponseDto(false, "SUCCESS", exam);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", exam);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
		
	}

	// find All
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	// update
	@Override
	public ResponseEntity<ResponseDto> update(@Valid ExamenDto dto) {
		boolean result = service.update(ExamenConverter.convertExamenDtoCreateToExamen(dto));
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete by Id
	@Override
	public ResponseEntity<ResponseDto> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
