package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modèle de l'entité Note
 * @author clara
 * @since 1.0.x
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn
	private Module module;
	@Column
	private Float valeur;
	@ManyToOne
	@JoinColumn
	private Etudiant etudiant;
	@ManyToOne
	@JoinColumn
	private Examen examen;

}
