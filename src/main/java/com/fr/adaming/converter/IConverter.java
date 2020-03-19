package com.fr.adaming.converter;

import java.util.List;


public interface IConverter<E, D> { //E = Entité / D = Dto
	
	public E dtoToEntite(D dto);
	public List<E> listDtoToEntite(List<D> liste);
	public D entiteToDto(E entite);
	public List<D> listEntiteToDto(List<E> liste);
}
