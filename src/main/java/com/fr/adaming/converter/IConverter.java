package com.fr.adaming.converter;

import java.util.List;


/**
 * Interface responsable de la conversion entre dto et entité
 * @author clara
 *
 * @param <E> La classe de l'entité pour la conversion
 * @param <D> La classe du DTO pour la conversion
 */
public interface IConverter<E, D> { //E = Entité / D = Dto
	
	/**
	 * Permet la conversion d'un Dto à une entité
	 * @param dto à convertir en entité
	 * @return l'entité de la conversion, null si le dto était null
	 */
	public E dtoToEntite(D dto);
	/**
	 * Permet la conversion d'une liste de Dto à une liste d'entité
	 * @param liste à convertir en liste d'entité
	 * @return la liste d'entité de la conversion, vide si la liste d'entrée était nulle
	 */
	public List<E> listDtoToEntite(List<D> liste);
	/**
	 * Permet la conversion d'une entité à un Dto
	 * @param entite à convertir en dto
	 * @return le dto de la conversion, null si l'entite était null
	 */
	public D entiteToDto(E entite);
	/**
	 * Permet la conversion d'une liste d'entité à une liste de Dto
	 * @param liste à convertir en liste de Dto
	 * @return la liste de dto de la conversion, vide si la liste d'entrée était nulle
	 */
	public List<D> listEntiteToDto(List<E> liste);
}
