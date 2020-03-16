package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Note;

@Repository
public interface INoteDao  extends JpaRepository<Note, Integer>{
	
	public List<Note> listByEtudiant(int etudiant);

}
