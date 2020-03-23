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
 * Modèle de l'entité Etudiant
 * 
 * @author clara
 * @since 1.0.x
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30)
	private String nom;
	@Column(length = 20)
	private String prenom;
	@Column(length = 40)
	private String adresse;
	@Column(length = 20)
	private String ville;
	@Column(length = 25)
	private String email;
	@Column
	private int codePostale;
	@Column
	private int cni;
	@Column
	private int telephone;
	
	/**
	 * Champ pour le sexe : true pour femme, false pour homme
	 */
	@Column
	private boolean sexe;
	@Column
	private boolean enEtude;

}
