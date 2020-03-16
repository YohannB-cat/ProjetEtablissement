package com.fr.adaming.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Examen;

public interface IExamenService{

	public Examen create(Examen exam);

	public List<Examen> findAll();

	public Examen findById(int id);

	public boolean update(Examen exam);

	public boolean deleteById(int id);

}
