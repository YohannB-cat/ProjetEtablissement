package com.fr.adaming.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereDtoCreate;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereCreateConverterTest {
	
	@Autowired
	public IConverter<Matiere, MatiereDtoCreate> convert;

}
