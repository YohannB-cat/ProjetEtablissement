package com.fr.adaming.config.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Classe StepListener responsable de l'entité étudiant : Permet d'afficher mode console le début, le status et la fin du step étudiant 
 * @author clara
 * @since 1.0.x
 */
@Component
public class EtudiantListener extends StepExecutionListenerSupport {
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("----> Début Step étudiant");
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if( stepExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println(" -----> Step étudiant success");
		} else if( stepExecution.getStatus() == BatchStatus.FAILED) {
			System.out.println(" -----> Step étudiant fail");
		}
		
		System.out.println("----> Fin Step étudiant");
		return null;

	}

}
