package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Modèle de l'entité Absence. Getter et Setter des attributs de l'entité. Constructeur vide et contructeurs variés avec certains ou tous les attributs
 * @author Isaline
 * @since 1.0.X
 *
 */
@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private LocalDate debut;
	@Column(nullable = false)
	private LocalDate fin;
	@Column(length = 200)
	private String justification;
	@Column(length = 200)
	private String description;
	
	@ManyToOne
	private Etudiant etudiant;

	public Absence(LocalDate debut, LocalDate fin, String justification, String description, Etudiant etudiant) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.justification = justification;
		this.description = description;
		this.etudiant = etudiant;
	}

	public Absence(LocalDate debut, LocalDate fin, String justification, String description) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.justification = justification;
		this.description = description;
	}

	public Absence(int id, LocalDate debut, LocalDate fin, String justification, String description) {
		super();
		this.id = id;
		this.debut = debut;
		this.fin = fin;
		this.justification = justification;
		this.description = description;
	}

	public Absence(int id, LocalDate debut, LocalDate fin) {
		super();
		this.id = id;
		this.debut = debut;
		this.fin = fin;
	}
	
	
	
}
