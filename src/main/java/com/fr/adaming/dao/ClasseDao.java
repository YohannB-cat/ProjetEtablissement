package com.fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Classe;

public class ClasseDao {
	private Session s = new Configuration().configure().buildSessionFactory().openSession();
	
	public List<Classe> listByNiveau(int id_niveau){
		String hql = "from Classe where classe_id =:idNiveau";
		Query<Object> query = s.createQuery(hql,Object.class);
		query.setParameter("idEtudiant", id_niveau);
		List<Classe> listNote= new ArrayList<Classe>();
		List<Object> listobj = query.getResultList();
		for (Object ligneAsObject:listobj) {
		    Object[] ligne = (Object[])ligneAsObject ;
		    int id = (int)ligne[0] ;
		    String nom = (String)ligne[2] ;
			List<Etudiant> etudiants = (List<Etudiant>)ligne[3];
		    listNote.add(new Classe(id,nom,etudiants));
		}
		return listNote;
	}

}
