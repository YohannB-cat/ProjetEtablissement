package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Etudiant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto de l'entité Classe (sans id)
 * @author Flavien
 * @since 1.0.x
 *
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ClasseDto {
	
	private String nom;
	
	private List<Etudiant> liste;

}
