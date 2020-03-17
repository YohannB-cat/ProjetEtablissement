package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceDtoCreate {
	
	private int id;
	
	@NotNull
	private String debut;

	@NotNull
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
