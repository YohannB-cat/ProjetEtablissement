package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.dao.INoteDao;
import com.fr.adaming.entity.Note;

import lombok.extern.slf4j.Slf4j;

@Service //("noteservice")
@Slf4j
public class NoteService implements INoteService {

	@Autowired
	private INoteDao noteDao;
	
	@Autowired
	private IEtudiantDao etudiantDao;

	@Override
	public Note create(Note note) {
		try {
			if (note == null || noteDao.existsById(note.getId())) {
				log.warn("Tentative vaine de creation d'une note");
				return null;
			}
			log.info("Creation d'une nouvelle note");
			return noteDao.save(note);
		} catch (DataIntegrityViolationException e) {
			log.error("DataIntegrityViolationException");
			return null;
		}catch (ConstraintViolationException er) {
			log.error("ConstraintViolationException");
			return null;
		}
	}

	@Override
	public List<Note> findAll() {
		if (noteDao.findAll().isEmpty()) {
			log.warn("La liste de note est vide");
			return new ArrayList<>();
		}
		log.info("Recuperation de la liste des notes");
		return noteDao.findAll();
	}

	@Override
	public Note findById(int id) {
		try {
			if (id != 0) {
				log.info("Recuperation d'une note");
				return noteDao.findById(id).orElse(null);
			} else {
				log.warn("Tentative de recuperation d'une note inexistante");
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("InvalidDataAccessApiUsageException");
			return null;
		}
	}

	@Override
	public boolean update(Note note) {
		try {
			if (noteDao.existsById(note.getId())) {
				noteDao.save(note);
				
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException er) {
			er.printStackTrace();
			return false;
		} catch (NullPointerException ec) {
			ec.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (noteDao.findById(id).isPresent()) {
				noteDao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return false;
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Note> listByEtudiant(int idetudiant){
		List<Note> listNote =null;
		try {
			if (etudiantDao.findById(idetudiant).isPresent()) {
				 listNote = noteDao.listByEtudiant(idetudiant);
			}
		}catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
		}
		return listNote;
	}
}
