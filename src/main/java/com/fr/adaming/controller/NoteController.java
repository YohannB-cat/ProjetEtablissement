package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Note;
import com.fr.adaming.service.INoteService;

@RestController
public class NoteController implements INoteController {

	@Autowired
//	@Qualifier("noteservice")
	private INoteService service;

	@Autowired
	private IConverter<Note, NoteDtoCreate> noteCreateDto;

	@Autowired
	private IConverter<Note, NoteDto> noteDto;

	// create
	@Override
	public ResponseEntity<ResponseDto> create(@Valid NoteDtoCreate dto) {
		NoteDtoCreate note = noteCreateDto.entiteToDto(service.create(noteCreateDto.dtoToEntite(dto)));

		// initialisation de la reponse
		ResponseDto resp = null;

		// Attribution de la réponse en fonction du retour DB et de la conposition de
		// l'objet
		if (note != null) {
			resp = new ResponseDto(false, "SUCCESS", note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", note);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

	}

	// find By Id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		NoteDto note = noteDto.entiteToDto(service.findById(id));

		// initialisation de la reponse
		ResponseDto resp = null;

		if (note != null) {
			resp = new ResponseDto(false, "SUCCESS", note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", note);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

	// find All
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<NoteDto> list = noteDto.listEntiteToDto(service.findAll());

		ResponseDto resp = null;
		if (list != null) {
			resp = new ResponseDto(false, "SUCCESS", list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

	}

	// Update
	@Override
	public ResponseEntity<ResponseDto> update(@Valid NoteDtoCreate dto) {
		boolean result = service.update(noteCreateDto.dtoToEntite(dto));

		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete By Id
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@Override
	public ResponseEntity<ResponseDto> listByEtudiant(int id_etudiant){
		ResponseDto resp = null;
		List<NoteDto> listNote = noteDto.listEntiteToDto(service.listByEtudiant(id_etudiant));
		if (listNote != null) {
			resp = new ResponseDto(false, "SUCCESS", listNote);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

}
