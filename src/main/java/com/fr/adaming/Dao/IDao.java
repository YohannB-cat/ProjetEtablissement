package com.fr.adaming.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IDao extends JpaRepository<Note, Integer> implements IDaoEtudiant{

	
	
}
