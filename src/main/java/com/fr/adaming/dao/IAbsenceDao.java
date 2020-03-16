package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Absence;

@Repository
public interface IAbsenceDao extends JpaRepository<Absence, Integer> {

}
