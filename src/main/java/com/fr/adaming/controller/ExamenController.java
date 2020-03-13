package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Examen;

public class ExamenController implements IExamenController {

	@Autowired
	@Qualifier("serviceexamen")
	private IServiceExamen service;

	// create
	@Override
	public ResponseEntity<ResponseDto> create(@Valid ExamenDtoCreate dto) {
		ExamenDto exam = ExamenConverter.convertExamentToExamenDto()service.create(ExamenConverter.convertExamenDtoToExamen(dto)));
		
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
	public ResponseEntity<ResponseDto> find(int id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	// delete by Id
	@Override
	public ResponseEntity<ResponseDto> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
