package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Modèle Dto pour ResponseDto : Error = false/true en cas d'erreur, message = SUCCESS/FAIL, objet pour pouvoir retourner n'importe quel Dto 
 * @author clara
 * @since 1.0.x
 */
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {
	
	private boolean error;
	
	private String message;
	
	private Object object;

}
