package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Matiere;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ModuleDtoCreate {
	private int id;
	
	private String nom;
	
	private List<Matiere> matiere;

	public ModuleDtoCreate(String nom) {
		super();
		this.nom = nom;
	}
	
	
	

}
