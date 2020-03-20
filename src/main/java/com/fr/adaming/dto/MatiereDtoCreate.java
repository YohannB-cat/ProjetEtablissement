package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MatiereDtoCreate {
	
	private Integer id;
	
	private String nom;

	public MatiereDtoCreate(String nom) {
		super();
		this.nom = nom;
	}
	
	
}
