package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Note;

@Repository
public interface INoteDao  extends JpaRepository<Note, Integer>{
	
	@Query(value = "from Note where etudiant_id = :etudiant")
	public List<Note> listByEtudiant(@Param(value = "etudiant") int etudiant);
}
