package com.fr.adaming.dto;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * Modèle du dto NoteDto (similaire à l'entité Note, mais sans id)
 * @author clara
 * @since 1.0.x
 */
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class NoteDto {
	private Module module;

	private float valeur;

	private Etudiant etudiant;

	private Examen examen;

}
