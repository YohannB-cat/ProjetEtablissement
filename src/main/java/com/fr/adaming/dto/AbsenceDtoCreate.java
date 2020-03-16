package com.fr.adaming.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceDtoCreate {
	
	private int id;
	
	private LocalDate debut;

	private LocalDate fin;

	private String justification;

	private String description;


}
