package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Classe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class NiveauDtoCreate {

	private List<Classe> classes;
	
	private int id;

	private String nom;
	

}
