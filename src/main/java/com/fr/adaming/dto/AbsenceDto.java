package com.fr.adaming.dto;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceDto {
	
	private String debut;

	private String fin;

	private String justification;

	private String description;
	
	private Etudiant etudiant;


}
