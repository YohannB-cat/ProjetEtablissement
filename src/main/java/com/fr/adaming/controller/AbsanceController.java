package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.dto.AbsanceDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IEtudiantService;

public class AbsanceController implements IAbsanceController {

	@Autowired
	@Qualifier("absanceservice")
	private IAbsanceService service;
	
	@Override
	public ResponseEntity<ResponseDto> create(@Valid AbsanceDto dto) {
		AbsanceDto etu = 
				AbsanceConverter.convertAbsanceToAbsanceDto()
				service.create(AbsanceConverter.convertAbsanceDtoToAbsance(dto)));
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
