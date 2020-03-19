package com.fr.adaming.service;

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

@Service //("noteservice")
@SuppressWarnings("squid:S1148")
public class NoteService implements INoteService {

	@Autowired
	private INoteDao noteDao;
	
	@Autowired
	private IEtudiantDao etudiantDao;

	@Override
	public Note create(Note note) {
		try {
			if (note == null || noteDao.existsById(note.getId())) {
				return null;
			}
			return noteDao.save(note);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		}catch (ConstraintViolationException er) {
			er.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Note> findAll() {
		if (noteDao.findAll().isEmpty()) {
			return null;
		}
		return noteDao.findAll();
	}

	@Override
	public Note findById(int id) {
		try {
			if (id != 0) {
				return noteDao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
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
	public List<Note> listByEtudiant(int id_etudiant){
		List<Note> listNote =null;
		try {
			if (etudiantDao.findById(id_etudiant).isPresent()) {
				 listNote = noteDao.listByEtudiant(id_etudiant);
			}
		}catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
		}
		return listNote;
	}
}
