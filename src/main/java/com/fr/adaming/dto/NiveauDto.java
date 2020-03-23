package com.fr.adaming.dto;

import java.util.List;

import com.fr.adaming.entity.Classe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto de l'entité Niveau (sans id)
 * @author Flavien
 * @since 1.0.x
 *
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class NiveauDto {

	private List<Classe> classes;

	private String nom;	

}
