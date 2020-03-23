package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Couche Dto pour créer et modifier des matières
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MatiereDtoCreate {
	
	private Integer id;
	
	private String nom;

	/**
	 * Constructeur sans id 
	 * @param nom correspond au nom de la matière
	 */
	public MatiereDtoCreate(String nom) {
		super();
		this.nom = nom;
	}
	
	
}
