package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.config.WebConstant;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Note;
import com.fr.adaming.service.INoteService;

@RestController
@RequestMapping(path = "/note")
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
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody NoteDtoCreate dto) {
		NoteDtoCreate note = noteCreateDto.entiteToDto(service.create(noteCreateDto.dtoToEntite(dto)));

		// initialisation de la reponse
		ResponseDto resp = null;

		// Attribution de la réponse en fonction du retour DB et de la conposition de
		// l'objet
		if (note != null) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, WebConstant.FAIL, note);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

	}

	// find By Id
	@Override
	@GetMapping (path = "/{id}")
	public ResponseEntity<ResponseDto> findById (@PathVariable (name = "id", required = false) int id) {
		NoteDto note = noteDto.entiteToDto(service.findById(id));

		// initialisation de la reponse
		ResponseDto resp = null;

		if (note != null) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, note);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, WebConstant.FAIL, note);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

	// find All
	@Override
	@GetMapping (path = "/all")
	public ResponseEntity<ResponseDto> findAll() {
		List<NoteDto> list = noteDto.listEntiteToDto(service.findAll());

		ResponseDto resp = null;
		if (list != null) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

	}

	// Update
	@Override
	@PutMapping
	public ResponseEntity<ResponseDto> update (@RequestBody NoteDtoCreate dto) {
		boolean result = service.update(noteCreateDto.dtoToEntite(dto));

		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete By Id
	@Override
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id){
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, WebConstant.SUCCESS, null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@Override
	@GetMapping(path = "/etudiant/{id}")
	public ResponseEntity<ResponseDto> listByEtudiant(@PathVariable(name = "id") int idetudiant){
		ResponseDto resp = null;
		List<NoteDto> listNote = noteDto.listEntiteToDto(service.listByEtudiant(idetudiant));
		if (listNote != null && !listNote.isEmpty()) {
			resp = new ResponseDto(false, WebConstant.SUCCESS, listNote);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, WebConstant.FAIL, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		
	}

}
