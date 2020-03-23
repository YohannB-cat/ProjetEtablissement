package com.fr.adaming.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yohann
 * @since 1.0.x 
 * Entité de transfert d'objet Examen côté controller avec getter et setter
 * Constructeur Vide : ExamenDto()
 * Constructeur Plein : ExamenDto(<b>int</b> id,<b>LocalDate</b> date, <b>String</b> type,  <b>Double</b> coefficient)
 *
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ExamenDto {
	
	private LocalDate date;
	
	private String type;
	
	private double coefficient;
 
}
