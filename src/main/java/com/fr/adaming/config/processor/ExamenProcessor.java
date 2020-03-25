package com.fr.adaming.config.processor;

import org.springframework.batch.item.ItemProcessor;

import com.fr.adaming.entity.Examen;

public class ExamenProcessor implements ItemProcessor<Examen, Examen>{


	@Override
	public Examen process(Examen item) throws Exception {
		
		return item;
	}

}
