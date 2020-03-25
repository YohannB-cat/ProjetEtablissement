package com.fr.adaming.config.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Etudiant;

/**
 * Classe Processor relative à l'entité Etudiant : Permet l'envoie d'un email de notification d'inscription à chaque étudiant
 * @author clara
 * @since 1.0.x
 */

@Component
public class EtudiantProcessor implements ItemProcessor<Etudiant, Etudiant> {
	
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public Etudiant process(Etudiant person) throws Exception {
		// Envoyer un mail de notification
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(person.getEmail());
		email.setSubject("Inscription à l'université Margoulin! :)");
		email.setText("Bonjour "+person.getNom()+" ! \n\n"
				+ "Bienvenue à l'université gérée par la PataTeam\n"
				+ "Cordialement\n");
		emailSender.send(email);

	    return person;
	}

	

}
