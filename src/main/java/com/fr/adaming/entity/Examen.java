package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Yohann
 * @since 1.0.x
 * Entité Examen avec Getter, Setter
 * Constructeur Vide : Examen()
 * Constructeur Plein : Examen(<b>int</b> id,<b>LocalDate</b> date, <b>String</b> type,  <b>Double</b> coefficient)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Examen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private LocalDate date;
	@Column(length = 20)
	private String type;
	@Column
	private Double coefficient;

}
