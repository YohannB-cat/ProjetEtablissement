package com.fr.adaming.converter;

public interface IConverterTest {
	
	public void testDtoToEntiteValid_shouldReturnEntite();
	public void testDtoToEntiteNotValid_shouldReturnNull();
	public void testDtoToEntiteNull_shouldReturnNull();
	
	
	public void entiteToDtoValid_shouldReturnEntite();
	public void entiteToDtoNotValid_shouldReturnNull();
	public void entiteToDtoNull_shouldReturnNull();
	

	public void listDtoToEntiteValid_shouldReturnEntite();
	public void listDtoToEntiteNotValid_shouldReturnNull();
	public void listDtoToEntiteNull_shouldReturnNull();
	

	public void listEntiteToDtoValid_shouldReturnEntite();
	public void listEntiteToDtoNotValid_shouldReturnNull();
	public void listEntiteToDtoNull_shouldReturnNull();
	

}
