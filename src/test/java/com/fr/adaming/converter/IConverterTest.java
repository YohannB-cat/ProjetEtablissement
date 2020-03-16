package com.fr.adaming.converter;

public interface IConverterTest {
	
	public void testDtoToEntiteValid_shouldReturnEntite();
	public void testDtoToEntiteNotValid_shouldReturnNull();
	public void testDtoToEntiteNull_shouldReturnNull();
	
	
	public void testEntiteToDtoValid_shouldReturnEntite();
	public void testEntiteToDtoNotValid_shouldReturnNull();
	public void testEntiteToDtoNull_shouldReturnNull();
	

	public void testListDtoToEntiteValid_shouldReturnEntite();
	public void testListDtoToEntiteNotValid_shouldReturnNull();
	public void testListDtoToEntiteNull_shouldReturnNull();
	

	public void testListEntiteToDtoValid_shouldReturnEntite();
	public void testListEntiteToDtoNotValid_shouldReturnNull();
	public void testListEntiteToDtoNull_shouldReturnNull();
	

}
