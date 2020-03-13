package com.fr.adaming.converter;

import java.util.List;

public interface IConverter<Entite, Dto> {
	
	public Entite EntiteToDto(Dto dto);
	public List<Entite> ListEntiteToDto(Dto dto);
	public Dto DtoToEntite(Entite entite);
	public List<Dto> ListDtoToEntite(Entite entite);
}
