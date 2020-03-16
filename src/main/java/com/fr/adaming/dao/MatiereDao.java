package com.fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fr.adaming.entity.Matiere;

public class MatiereDao {
	private Session s = new Configuration().configure().buildSessionFactory().openSession();

	public List<Matiere> listByModule(int id_module){
		String hql = "from Matiere where id_module =:idModule";
		Query<Object> query = s.createQuery(hql,Object.class);
		query.setParameter("idModule", id_module);
		List<Matiere> listMatiere= new ArrayList<Matiere>();
		List<Object> listobj = query.getResultList();
		for (Object ligneAsObject:listobj) {
		    Object[] ligne = (Object[])ligneAsObject ;
		    int id = (int)ligne[0] ;
		    String nom = (String)ligne[1] ;
		    listMatiere.add(new Matiere(id,nom));
		}
		return listMatiere;
	}

}
