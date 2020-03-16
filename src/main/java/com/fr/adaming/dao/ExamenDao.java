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
	
	public List<Examen> listByMatiere(int id_matiere){
		String hql = "select e.id,e.date, e.type, e.coefficient from examen e,note n,module mo,matiere ma where mo.id=ma.matiere_id and n.module_id=mo.id and n.examen_id=e.id and ma.id =:idmatiere";
		Query<Object> query = s.createQuery(hql,Object.class);
		query.setParameter("idmatiere", id_matiere);
		List<Examen> listExam= new ArrayList<Examen>();
		List<Object> listobj = query.getResultList();
		for (Object ligneAsObject:listobj) {
		    Object[] ligne = (Object[])ligneAsObject ;
		    int id = (int)ligne[0] ;
		    LocalDate date = (LocalDate)ligne[1] ;  //Attention conversion de sql date à localdate (est-ce que ca marche ?)
		    String type = (String)ligne[2] ;
		    double coefficient = (double)ligne[3];
		    listExam.add(new Examen(id,date,type,coefficient));
		}
		return listExam;
	}

}
