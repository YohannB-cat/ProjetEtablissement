package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;
@Repository
public interface IExamenDao  extends JpaRepository<Examen, Integer>{	

}
