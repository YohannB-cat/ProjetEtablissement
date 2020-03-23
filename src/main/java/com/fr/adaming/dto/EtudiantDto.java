package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modèle du dto EtudiantDto (similaire à étudiant mais sans id)
 * @author clara
 * @since 1.0.x
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EtudiantDto {
	
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	private int codePostal;
	
	private String ville;
	
	private boolean sexe;
	
	private int cni;
	
	private int telephone;
	
	private String email;
	
	private boolean enEtude;

}
