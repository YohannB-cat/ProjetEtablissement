package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;

public class AbsanceController implements IAbsanceController {

	@Override
	public ResponseEntity<ResponseDto> create(@Valid AbsanceDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto> update(@Valid AbsanceDtoCreate dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findById(int id) {
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
