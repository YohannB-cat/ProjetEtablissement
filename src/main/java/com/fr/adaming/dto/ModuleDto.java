package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Matiere;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Couche Dto simple pour module
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ModuleDto {
	private String nom;
	
	private List<Matiere> matiere;

	/**
	 * Constructeur sans id 
	 * @param nom correspond au nom du module
	 */
	public ModuleDto(String nom) {
		super();
		this.nom = nom;
	}
	

}
