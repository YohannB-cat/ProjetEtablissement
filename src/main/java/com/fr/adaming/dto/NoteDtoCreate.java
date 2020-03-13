package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class NoteDtoCreate {
	
	private int id;
	
	private int etudiant;
	
	private int module;
	
	private float valeur;

}



