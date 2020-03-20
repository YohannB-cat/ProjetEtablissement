package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Classe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class NiveauDto {

	private List<Classe> classes;

	private String nom;
	

}
