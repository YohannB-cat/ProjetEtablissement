package com.fr.adaming.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

public class ExamenDao{
	
	private Session s = new Configuration().configure().buildSessionFactory().openSession();
	
	// Recupere la liste des examens (date type coef) d'une matiere 
	public List<Examen> listByMatiere(int id_matiere){
		String hql = "select e.id,e.date, e.type, e.coefficient from examen e,note n,module mo,matiere ma where mo.id=ma.matiere_id and n.module_id=mo.id and n.examen_id=e.id and ma.id =:idmatiere";
		//		hql = "select * from Examen where note=(select note";
		Query<Object> query = s.createQuery(hql,Object.class);
//		query.setParameter("idmatiere", matiere.getId());
		query.setParameter("idmatiere", id_matiere);
		List<Examen> listExam= new ArrayList<Examen>();
		List<Object> listobj = query.getResultList();
		for (Object ligneAsObject:listobj) {// ligne correspond à une des lignes du résultat
		    Object[] ligne = (Object[])ligneAsObject ;
			
		     // cette liste est composée de deux éléments : nom et prenom
		    int id = (int)ligne[0] ;
		    LocalDate date = (LocalDate)ligne[1] ;
		    String type = (String)ligne[2] ;
		    double coefficient = (double)ligne[3];
		    listExam.add(new Examen(id,date,type,coefficient));
		}
		return listExam;
	}

}
