package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Note;
import com.fr.adaming.service.INoteService;

/**
 * Classe Spring Batch Writer de l'entité Note
 * @author Yohann
 * @since 1.0.x
 *
 */
@Component
public class NoteWriter implements ItemWriter<Note> {
	
	@Autowired
	private INoteService service;

	@Override
	public void write(List<? extends Note> items) throws Exception {
		for (Note note : items) {
			service.create(note);
		}
		
	}
	
	

}
