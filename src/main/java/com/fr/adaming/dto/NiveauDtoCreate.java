package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Classe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DtoCreate de l'entité Niveau (avec id)
 * @author Flavien
 * @since 1.0.x
 *
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class NiveauDtoCreate {

	private List<Classe> classes;
	
	private int id;

	private String nom;	

}
