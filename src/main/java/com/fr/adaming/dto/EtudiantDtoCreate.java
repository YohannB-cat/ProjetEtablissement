package com.fr.adaming.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EtudiantDtoCreate {
	
	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	private int codePostal;
	
	private String ville;
	
	private boolean sexe;
	
	private int cni;
	@ApiModelProperty(example = "0645454545")
	private int telephone;
	
	private String email;
	
	private boolean enEtude;


}
