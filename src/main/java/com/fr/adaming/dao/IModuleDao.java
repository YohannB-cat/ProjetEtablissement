package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Module;

/**
 * Couche Repository pour module
 * @author IN-LY-004
 * @since 1.0.x
 */
@Repository
public interface IModuleDao extends JpaRepository<Module, Integer> {

}
