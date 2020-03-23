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
 * Entité de transfert d'objet Examen côté controller pour la création de nouveaux objets uniquement avec getter et setter
 * Constructeur Vide : ExamenDtoCreate()
 * Constructeur Plein : ExamenDtoCreate(<b>int</b> id,<b>LocalDate</b> date, <b>String</b> type,  <b>Double</b> coefficient)
 *
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ExamenDtoCreate {
	
	private int id;
	
	private LocalDate date;
	
	private String type;
	
	private double coefficient;
 

}
