package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entité Module
 * 
 * @author IN-LY-004
 * @since 1.0.x
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 30)
	private String nom;
	@OneToMany
	@JoinColumn(name = "module_id")
	private List<Matiere> matieres;

	/**
	 * Constructeur sans id
	 * 
	 * @param nom      correspond au nom du module
	 * @param matieres correspond à la lsite des matières du module
	 */
	public Module(String nom, List<Matiere> matieres) {
		super();
		this.nom = nom;
		this.matieres = matieres;
	}

	/**
	 * Constructeur sans id, sans liste des matières
	 * 
	 * @param nom correspond au nom du module
	 */
	public Module(String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * Constructeur sans liste des matières
	 * 
	 * @param id  correspond à l'id du module
	 * @param nom correspond au nom du module
	 */
	public Module(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

}
