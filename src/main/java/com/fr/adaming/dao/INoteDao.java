package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Note;

public interface INoteDao  extends JpaRepository<Note, Integer>{
	
	public void findByEtudiant(Etudiant etudiant);
	
	public Note findByIdEtudiant (int etudiant);
	
	public boolean deleteByIdEtudiant (int etudiant);

}
