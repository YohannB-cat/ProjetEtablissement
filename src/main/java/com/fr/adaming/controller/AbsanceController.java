package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.AbsanceConverter;
import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.dto.AbsanceDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IEtudiantService;

@RestController
@RequestMapping(path = "/absance")
public class AbsenceController implements IAbsenceController {

	@Autowired
	@Qualifier("absanceservice")
	private IAbsenceService service;

	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid AbsenceDto dto) {
		AbsanceDto etu = 
				AbsanceConverter.convertAbsenceToAbsenceDto()
				service.create(AbsenceConverter.convertAbsenceDtoToAbsence(dto)));
		
		ResponseDto resp = null;
		
		if (etu != null) {
			resp = new ResponseDto(false, "SUCCESS", etu);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", etu);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid AbsenceDtoCreate dto) {
		boolean result = service.update(AbsenceConverter.convertAbsenceDtoCreateToAbsence(dto));
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
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
