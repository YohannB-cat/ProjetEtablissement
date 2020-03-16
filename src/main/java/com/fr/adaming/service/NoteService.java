package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.fr.adaming.Dao.INoteDao;
import com.fr.adaming.entity.Note;

public class NoteService implements INoteService {

	@Autowired
	private INoteDao dao;

	@Override
	public Note create(Note note) {
		try {
			if (note == null || dao.existsById(note.getId())) {
				return null;
			}
			return dao.save(note);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		}

		catch (ConstraintViolationException er) {
			er.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Note> findAll() {
		if (dao.findAll().isEmpty()) {
			return null;
		}
		return dao.findAll();
	}

	@Override
	public Note findById(int id) {
		try {
			if (id != 0) {
				return dao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Note findByEtudiant(int etudiant) {
		if (etudiant != 0) {
			return dao.findByIdEtudiant(etudiant);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Note note) {
		try {
			if (dao.existsById(note.getId())) {
				dao.save(note);
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
			if (dao.findById(id) != null && id != 0) {
				dao.deleteById(id);
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
	public boolean deleteByEtudiant(int etudiant) {
		try {
			if (dao.findByIdEtudiant(etudiant) != null && etudiant != 0) {
				return dao.deleteByIdEtudiant(etudiant);
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

}
