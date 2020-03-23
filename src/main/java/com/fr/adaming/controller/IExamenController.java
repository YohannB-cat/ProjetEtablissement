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
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.ExamenDtoCreate;
import com.fr.adaming.dto.ResponseDto;


/**
 * Interface IExamenController reponsable de la couche controller de l'entité Examen
 * @author Yohann
 * @since 1.0.x
 *
 */
@RequestMapping (path ="examen/")
public interface IExamenController {
	

	
	
	//create examen 
	/**
	 * Méthode create qui prends une entité de création en paramètre et qui renvoie un objet de réponse qui correspond à un fail si l'objet n'est pas créé et un success si l'objet a bien été créé.
	 * Utilisation de la méthode de requete Post.
	 * @param dto dto de l'entité à créer
	 * @return ResponseEntity de type ResponseDto(<b>boolean</b> error, <b>String</b> message, <b>Object</b> object créé)
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid ExamenDtoCreate dto);
	
	//read examen 
	/**
	 * Méthode finById qui cherche une instance par son Id en paramètre et qui renvoie un objet de réponse qui correspond à un success si l'instance est trouvé et un à un fail sinon.
	 * Utilisation de la méthode de requete Get.
	 * @param id identifiant de l'entité à trouver
	 * @return ResponseEntity de type ResponseDto(<b>boolean</b> error, <b>String</b> message, <b>Object</b> object trouvé)
	 */
	@GetMapping (path = "{id}")
	public  ResponseEntity<ResponseDto> findById(@PathVariable (name = "id", required = false) Integer id);
	
	//read all examen
	/**
	 * Méthode findAll qui sort la liste des examens compris dans une réponse de type success
	 * Utilisation de la méthode de requete Get
	 * @return ResponseEntity de type ResponseDto(<b>boolean</b> error, <b>String</b> message, <b>Object</b> Liste des examens)
	 */
	@GetMapping (path = "all")
	public  ResponseEntity<ResponseDto> findAll();
	
	//update examen
	/**
	 * Méthode update qui prends une entité de création en paramètre et qui renvoie un objet réponse qui correspond à un success si l'objet existait et a été mis à jour et un fail sinon
	 * Utilisation de la méthode de requete Put
	 * @param dto dto de l'entité à modifier
	 * @return ResponseEntity de type ResponseDto(<b>boolean</b> error, <b>String</b> message, <b>Object</b> objet modifié)
	 */
	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody @Valid ExamenDtoCreate dto);
	
	//delete examen
	/**
	 * Méthode delete qui supprime un objet de la DB et qui renvoie un objet de réponse qui correspond à un success si l'objet existait et a été supprimé et à un fail sinon
	 * Utilisation de la méthode de requete Delete.
	 * @param id identifiant de l'entité à supprimer
	 * @return ResponseEntity de type ResponseDto(<b>boolean</b> error, <b>String</b> message, <b>Object</b> object supprimé)
	 */
	@DeleteMapping (path ="{id}")
	public  ResponseEntity<ResponseDto> deleteById(@PathVariable(name = "id") int id);
	
	// Afficher la liste des examens par matiere
	/**
	 * Méthode listByMatiere qui sort une liste d'examens correspondant à une matière précise et qui renvoie un objet de réponse qui correspond à un success si la matière existe et un fail sinon
	 * Utilisation de la méthode de requete Get.
	 * @param idMatiere identifiant de la matiere dont il faut afficher la liste des examens
	 * @return ResponseEntity de type ResponseDto(<b>boolean</b> error, <b>String</b> message, <b>Object</b> Liste d'examens)
	 */
	@GetMapping(path = "matiere")
	public ResponseEntity<ResponseDto> listByMatiere(@RequestParam(name = "id") int idMatiere);

}
