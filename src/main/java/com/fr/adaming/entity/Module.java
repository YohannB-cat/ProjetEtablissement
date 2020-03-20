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
	
	public Module(String nom, List<Matiere> matieres) {
		super();
		this.nom = nom;
		this.matieres = matieres;
	}

	public Module(String nom) {
		super();
		this.nom = nom;
	}

	public Module(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}



	
	
}
