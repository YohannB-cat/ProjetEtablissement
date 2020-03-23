package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Matiere;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Couche dto Create pour les modules
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ModuleDtoCreate {
	private int id;
	
	private String nom;
	
	private List<Matiere> matiere;

	/**
	 * Constructeur sans id
	 * @param nom correspond au nom du module
	 */
	public ModuleDtoCreate(String nom) {
		super();
		this.nom = nom;
	}
	
	
	

}
