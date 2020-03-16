package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Niveau;

@Repository
public interface INiveauDao extends JpaRepository<Niveau, Integer>{
}
