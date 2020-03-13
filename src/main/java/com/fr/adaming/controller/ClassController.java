package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.ClasseDto;
import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.dto.ResponseDto;

public class ClassController implements IClasseController {

	@Override
	public ResponseEntity<ResponseDto> create(@Valid ClasseDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> update(@Valid ClasseDtoCreate dto) {
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
