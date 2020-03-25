package com.fr.adaming.config.listener;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Cette classe sert de listener(SpringBatch) à l'entité Absence
 * @author Isaline
 * @since 1.0.X
 */
@Component
public class AbsenceListener extends JobExecutionListenerSupport {

	/**
	 * Cette méthode permet de clarifier l'affichage de la réalisation des jobs dans la console
	 * APRES LE JOB
	 * @param jobExecution 
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("########## job terminé " + new Date() + " ##########");
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("---->>> job success");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			System.out.println("---->>> job échec");
		}
	}

	/**
	 * Cette méthode permet de clarifier l'affichage de la réalisation des jobs dans la console
	 * AVANT LE JOB
	 * @param jobExecution 
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("---->>> Job débute");
	}
	
}