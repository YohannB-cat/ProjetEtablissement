package com.fr.adaming.controller;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.dto.ResponseDto;



/**
 * <b>Interface Controller Matière</b>
 * méthodes CRUD et Find matieres by modules
 * @author Max
 * @since 1.0.x
 *
 */
@RequestMapping(path = "matiere/")
public interface IMatiereController {
	
	
	
	// create matiere
	
	/**
	 * Création d'une matière par un dto
	 * @param dto objet avec tout les attributs
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@PostMapping (path="create")
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid MatiereDtoCreate dto);

	// read matiere by id
	/**
	 * Trouvé une matière par un id
	 * @param id correspondant à l'identifiant de la matière
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@GetMapping (path = "{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable (name = "id", required = false) Integer id);
	
	// read all
	/**
	 * Affiche la liste de toutes les matières sans paramèters d'entrée
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@GetMapping (path = "all")
	public ResponseEntity<ResponseDto> findAll();
	
	//find liste matiere by module
	/**
	 * Affiche la lsite des matières correspondant à un module
	 * @param matieres corresond à l'id du module
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@GetMapping (path = "bymodule/{matieres}")
	public ResponseEntity<ResponseDto> findMatiereByMatieres (int matieres);
	
	// update matiere by id
	/**
	 * Modifie une entité dans la DB
	 * @param dto objet avec tout les attributs
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@PutMapping (path="update")
	public ResponseEntity<ResponseDto> update(@RequestBody MatiereDtoCreate dto);
	
	// delete matiere by id
	/**
	 * Supprime une matière par un id
	 * @param id correspond à l'id de la matière
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@DeleteMapping (path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);
	

}
