package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.dto.ResponseDto;

public interface IAbsenceController {

	public ResponseEntity<ResponseDto> create(@Valid @RequestBody AbsenceDtoCreate dto);

	public ResponseEntity<ResponseDto> update(@Valid @RequestBody AbsenceDtoCreate dto);

	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id);

	public ResponseEntity<ResponseDto> findAll();

	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id);

}
