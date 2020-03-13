package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IMatiereService;

public class MatiereController implements IMatiereController{
	
	@Autowired
	@Qualifier("servicematiere")
	private IMatiereService service;

	//create matiere
	@Override
	public ResponseEntity<ResponseDto> create(@Valid MatiereDtoCreate dto) {
		// TODO Auto-generated method stub
		return null;
	}

	//find by id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//find by nom
	@Override
	public ResponseEntity<ResponseDto> findByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	//find all
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//udpdate
	@Override
	public ResponseEntity<ResponseDto> update(MatiereDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	//delete By ID
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//delete By Nom
	@Override
	public ResponseEntity<ResponseDto> deleteByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
