package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class MatiereDtoCreate {
	
	private int id;
	
	private String nom;

	public MatiereDtoCreate(String nom) {
		super();
		this.nom = nom;
	}
	
	
}
