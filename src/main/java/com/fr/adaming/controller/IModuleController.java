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

import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * <b>Interface Controller Module</b>
 * methodes CRUD
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@RequestMapping (path = "module/")
public interface IModuleController {
	

	
	
	
	//create module
	/**
	 * Création d'un module
	 * @param dto objet avec tout les attributs
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid ModuleDtoCreate dto);
	
	//read module by id
	/**
	 * Trouvé un module par un id
	 * @param id correspondant à l'identifiant de la matière
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@GetMapping (path = "{id}")
	public ResponseEntity<ResponseDto> findById (@PathVariable(name = "id", required = false) int id);
	
	
	//read all
	/**
	 *  Affiche la liste de tous les modules sans paramètres d'entrée
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@GetMapping (path = "all")
	public  ResponseEntity<ResponseDto> findAll();
	
	//update module
	/**
	 *  Modifie une entité dans la DB
	 * @param dto objet avec tout les attributs
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@PutMapping
	public ResponseEntity<ResponseDto> update (@RequestBody @Valid ModuleDtoCreate dto);
		
	//delete module by id
	/**
	 * Supprime un module par un id
	 * @param id correspond à l'id du module
	 * @return objet Dto contenant un message, un objet et un booléen
	 */
	@DeleteMapping (path = "{id}")
	public ResponseEntity<ResponseDto> deleteById (@PathVariable(name = "id") int id);
	
	

}
