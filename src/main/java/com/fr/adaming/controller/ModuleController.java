package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.dto.ResponseDto;

public class ModuleController implements IModuleController{
	
	@Autowired
	@Qualifier("servicemodule")
	private IServiceModule service;

	//create
	@Override
	public ResponseEntity<ResponseDto> create(ModuleDtoCreate dto) {
		// TODO Auto-generated method stub
		return null;
	}

	//find By Id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//find By Nom
	@Override
	public ResponseEntity<ResponseDto> findByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	// Find All
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//Update
	@Override
	public ResponseEntity<ResponseDto> update(ModuleDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	//Delete By Id
	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//Delete By Nom
	@Override
	public ResponseEntity<ResponseDto> deleteByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

}
