package com.fr.adaming.dto;

import java.time.LocalDate;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceDto {
	
	private LocalDate debut;

	private LocalDate fin;

	private String justification;

	private String description;
	
	private Etudiant etudiant;


}
