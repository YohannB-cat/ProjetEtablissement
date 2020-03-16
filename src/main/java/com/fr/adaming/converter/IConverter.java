package com.fr.adaming.converter;

import java.util.List;

public interface IConverter<Entite, Dto> {
	
	public Entite entiteToDto(Dto dto);
	public List<Entite> listEntiteToDto(List<Dto> liste);
	public Dto dtoToEntite(Entite entite);
	public List<Dto> listDtoToEntite(List<Entite> liste);
}
