package com.fr.adaming.config.listener;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Listener de l'entité Classe pour le SpringBatch
 * @author Flavien
 * @since 1.0.x
 *
 */
@Component
public class ClasseListener extends JobExecutionListenerSupport {
	
	/**
	 * Methode listenerBeforeJob pour SpringBatch 
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("---->>> Job classe débute");
	}
	
	/**
	 * Methode listenerAfterJob pour SpringBatch, retourne un message différent dans la console suivant la réussite.
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("########## job classe terminé "+new Date()+ " ##########");

		if( jobExecution.getStatus() == BatchStatus.COMPLETED ){
			System.out.println("---->>> job classe success");

			} else if(jobExecution.getStatus() == BatchStatus.FAILED){
			System.out.println("---->>> job classe échec");
		}
	}
}
