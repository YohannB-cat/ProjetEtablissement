package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Absence;

/**
 * Cette interface gère la couche DAO de l'entité absence
 * @author Isaline
 * @since 1.0.X
 *
 */
@Repository
public interface IAbsenceDao extends JpaRepository<Absence, Integer> {

}
