package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.NoteDto;
import com.fr.adaming.dto.ResponseDto;

public class NoteController implements INoteController{

	//create
	@Override
	public ResponseEntity<ResponseDto> create(@Valid ExamenDtoCreate dto) {
		// TODO Auto-generated method stub
		return null;
	}

	//find By Id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//fidn By Etudiant
	@Override
	public ResponseEntity<ResponseDto> findByEtudiant(int etudiant) {
		// TODO Auto-generated method stub
		return null;
	}

	//find All
	@Override
	public ResponseEntity<ResponseDto> findAll(List<Note> listeNote) {
		// TODO Auto-generated method stub
		return null;
	}

	//Update
	@Override
	public ResponseEntity<ResponseDto> update(@Valid NoteDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	//delete By Id
	@Override
	public ResponseEntity<ResponseDto> deleteNoteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//Delete By Etudiant
	@Override
	public ResponseEntity<ResponseDto> deleteNoteByEtudiant(int etudiant) {
		// TODO Auto-generated method stub
		return null;
	}





}
