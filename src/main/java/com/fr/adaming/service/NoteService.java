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
		}catch (InvalidDataAccessApiUsageException err) {
			log.error("InvalidDataAccessApiUsageException : utilisation d'un objet en attribut qui n'existe pas dans la DB");
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
				log.info("SUCCESS update note");
				return true;
			} else {
				log.warn("FAIL update note");
				return false;
			}
		} catch (InvalidDataAccessApiUsageException er) {
			log.error("ERROR update note"+er.getMessage());
			return false;
		} catch (NullPointerException ec) {
			log.error("ERROR update nore"+ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (noteDao.findById(id).isPresent()) {
				noteDao.deleteById(id);
				log.info("SUCCESS delete note by id");
				return true;
			} else {
				log.info("FAIL delete note by id");
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("ERROR DELETE BY ID"+e.getMessage());
			return false;
		} catch (EmptyResultDataAccessException er) {
			log.error("ERROR DELETE BY ID"+er.getMessage());
			return false;
		}
	}
	
	@Override
	public List<Note> listByEtudiant(int idetudiant){
		List<Note> listNote =null;
		try {
			if (etudiantDao.findById(idetudiant).isPresent()) {
				log.info("SUCCESS lsit by etudiant");
				 return  noteDao.listByEtudiant(idetudiant);
			}
		}catch (InvalidDataAccessApiUsageException e) {
			log.error("ERROR list by etudiant"+e.getMessage());
		} catch (EmptyResultDataAccessException er) {
			log.error("ERROR list by etudiant"+er.getMessage());
		}
		return listNote;
	}
}
