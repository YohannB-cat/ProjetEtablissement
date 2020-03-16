package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Absence;

public interface IAbsenceDao extends JpaRepository<Absence, Integer> {

}
