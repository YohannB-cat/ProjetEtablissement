package com.fr.adaming.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ExamenDtoCreate {
	
	private int id;
	
	private LocalDate date;
	
	private String type;
	
	private double coefficient;
 

}
