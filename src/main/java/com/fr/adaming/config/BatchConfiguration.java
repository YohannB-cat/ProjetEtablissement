package com.fr.adaming.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.config.listener.EtudiantListener;
import com.fr.adaming.config.processor.EtudiantProcessor;
import com.fr.adaming.config.reader.EtudiantReader;
import com.fr.adaming.config.writer.EtudiantWriter;

public class BatchConfiguration {
	
	@Autowired
	public EtudiantReader etReader;
	
	@Autowired
	public EtudiantWriter etWriter;
	
	@Autowired
	public EtudiantProcessor etProcessor;
	
	@Autowired
	public EtudiantListener etListener;

}
