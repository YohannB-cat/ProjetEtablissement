package com.fr.adaming.entity;

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
 * Entité Matière
 * 
 * @author IN-LY-004
 * @since 1.0.x
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 40)
	private String nom;

	/**
	 * Constructeur sans id pour matière
	 * 
	 * @param nom correspond au nom de la matière
	 */
	public Matiere(String nom) {
		super();
		this.nom = nom;
	}

}
