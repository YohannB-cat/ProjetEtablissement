package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
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
