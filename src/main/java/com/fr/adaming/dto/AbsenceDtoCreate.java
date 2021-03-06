package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Modèle DTO de l'entité Absence AVEC l'identifiant unique de la BD. Getter et Setter de chaque attribut. Constructeur vide et constructeurs avec certains ou tous les attributs. 
 * @author Isaline
 * @since 1.0.X
 *
 */
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class AbsenceDtoCreate {
	
	private int id;
	
//	@NotNull
	private String debut;

//	@NotNull
	private String fin;

	private String justification;

	private String description;
	
	private Etudiant etudiant;

	public AbsenceDtoCreate(int id, @NotNull String debut, @NotNull String fin, String justification, String description) {
		super();
		this.id = id;
		this.debut = debut;
		this.fin = fin;
		this.justification = justification;
		this.description = description;
	}

}
