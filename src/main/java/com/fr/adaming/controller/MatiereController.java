package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.ExamenConverter;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.converter.MatiereConverter;
import com.fr.adaming.dto.ExamenDto;
import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.MatiereDto;
import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.IMatiereService;

@RestController
public class MatiereController implements IMatiereController {

	@Autowired
	@Qualifier("matiereservice")
	private IMatiereService service;
	
	@Autowired
	private IConverter<Matiere, MatiereDtoCreate> matiereCreateDto;
	
	@Autowired
	private IConverter<Matiere, MatiereDto> matiereDto;
	

	// create matiere
	@Override
	public ResponseEntity<ResponseDto> create(@Valid MatiereDtoCreate dto) {
		MatiereDtoCreate matiere = matiereCreateDto.entiteToDto(service.create(matiereCreateDto.dtoToEntite(dto)));
				
		
		//initialisation de la reponse
		ResponseDto resp = null;
		
		//Attribution de la réponse en fonction du retour DB et de la conposition de l'objet
		if (matiere!= null) {
			resp = new ResponseDto(false, "SUCCESS", matiere);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", matiere);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// find by id
	@Override
	public ResponseEntity<ResponseDto> findById(int id) {
		MatiereDto matiere =  matiereDto.entiteToDto(service.findById(id));
	
		// initialisation de la reponse
		ResponseDto resp = null;

		if (matiere != null) {
			resp = new ResponseDto(false, "SUCCESS", matiere);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", matiere);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	// find all
	@Override
	public ResponseEntity<ResponseDto> findAll() {
		List<MatiereDto> list = matiereDto.listEntiteToDto(service.findAll());
				
		
		ResponseDto resp = null;
		if (list != null) {
			resp = new ResponseDto(false, "SUCCESS", list);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	
	
	//find matiere by module
	@Override
	public ResponseEntity<ResponseDto> findMatiereByMatieres(int matieres) {
		List<MatiereDto> listMatiere = matiereDto.listEntiteToDto(service.findMatiereByIdModule(matieres));
		
		ResponseDto resp = null;
		
		if (listMatiere != null) {
			resp = new ResponseDto(false, "SUCCESS", listMatiere);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(false, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	
	

	// udpdate
	@Override
	public ResponseEntity<ResponseDto> update(MatiereDtoCreate dto) {
		boolean isSuccess = service.update(matiereCreateDto.dtoToEntite(dto));
				
				
		ResponseDto resp = null;

		if (isSuccess) {
			resp = new ResponseDto(false, "SUCCESS", isSuccess);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		resp = new ResponseDto(true, "FAIL", null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	// delete By ID
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

	

}
