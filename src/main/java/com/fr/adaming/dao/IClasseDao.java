package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;

@Repository
public interface IClasseDao  extends JpaRepository<Classe, Integer> {
}
