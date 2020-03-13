package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;

public class ExamenController implements IExamenController{

	@Override
	public ResponseEntity<ResponseDto> createExamen(@Valid ExamenDtoCreate dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> readExamen(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> readAll(List<Examen> listeExamen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> update(@Valid ExamenDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
