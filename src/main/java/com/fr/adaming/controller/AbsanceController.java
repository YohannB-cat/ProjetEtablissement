package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.AbsanceConverter;
import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.dto.AbsanceDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IEtudiantService;

@RestController
@RequestMapping(path = "/absance")
public class AbsenceController implements IAbsenceController {

	@Autowired
	@Qualifier("absenceservice")
	private IAbsenceService service;

	@Override
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid AbsenceDto dto) {
		AbsenceDto etu = 
				AbsenceConverter.convertAbsenceToAbsenceDto().
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
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> findById(int id) {
		AbsenceDtoCreate dto = AbsenceConverter.convertAbsenceToAbsenceDtoCreate(service.findById(id));
		ResponseDto resp = null;

		if (dto != null) {
			resp = new ResponseDto(false, "SUCCESS", dto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", dto);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@Override
	public void findAll() {
		List<EtudiantDtoCreate> list = service.findAll();

		ResponseDto resp = new ResponseDto(false, "SUCCESS", list);
	}

	@Override
	public ResponseEntity<ResponseDto> delete(int id) {
		boolean result = service.delete(id);
		ResponseDto resp = null;

		if (!result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
}
