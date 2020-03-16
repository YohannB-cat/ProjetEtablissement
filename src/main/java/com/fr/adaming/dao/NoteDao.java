package com.fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Module;
import com.fr.adaming.entity.Note;


public class NoteDao {
	private Session s = new Configuration().configure().buildSessionFactory().openSession();

	public List<Note> listByEtudiant(int id_etudiant){
		String hql = "from Note where etudiant_id =:idEtudiant";
		Query<Object> query = s.createQuery(hql,Object.class);
		query.setParameter("idEtudiant", id_etudiant);
		List<Note> listNote= new ArrayList<Note>();
		List<Object> listobj = query.getResultList();
		for (Object ligneAsObject:listobj) {
		    Object[] ligne = (Object[])ligneAsObject ;
		    int id = (int)ligne[0] ;
		    Module module = (Module)ligne[1] ;
		    float valeur = (float)ligne[2] ;
		    Etudiant etudiant = (Etudiant)ligne[3];
		    Examen examen = (Examen)ligne[4];
		    listNote.add(new Note(id,module,valeur,etudiant,examen));
		}
		return listNote;
	}
}
