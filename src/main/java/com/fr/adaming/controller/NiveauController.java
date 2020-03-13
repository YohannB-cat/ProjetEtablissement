package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;

public class NiveauController implements INiveauController {

	@Override
	public ResponseEntity<ResponseDto> create(@Valid NiveauDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> update(@Valid NiveauDtoCreate dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findByName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
