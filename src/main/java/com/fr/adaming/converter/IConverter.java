package com.fr.adaming.converter;

import java.util.List;

public interface IConverter<Entite, Dto> {
	
	public Entite EntiteToDto(Dto dto);
	public List<Entite> ListEntiteToDto(List<Dto> liste);
	public Dto DtoToEntite(Entite entite);
	public List<Dto> ListDtoToEntite(List<Entite> liste);
}
