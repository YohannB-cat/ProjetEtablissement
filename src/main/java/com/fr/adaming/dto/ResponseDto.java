package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {
	
	private boolean error;
	
	private String message;
	
	private Object object;

}
