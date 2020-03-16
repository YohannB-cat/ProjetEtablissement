package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Classe;

public interface IClasseService {

	public Classe create(Classe classe);

	public List<Classe> findAll();

	public Classe findById(int id);

	public boolean update(Classe classe);

	public boolean deleteById(int id);

}
