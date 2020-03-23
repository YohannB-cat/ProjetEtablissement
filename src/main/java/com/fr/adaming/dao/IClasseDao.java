package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;

/**
 * Interface IClasseDao reponsable de la couche dao de l'entité Classe. Pas de methodes particulières ajouté.
 * @author Flavien
 * @since 1.0.x
 *
 */
@Repository
public interface IClasseDao  extends JpaRepository<Classe, Integer> {
}
