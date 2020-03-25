package com.fr.adaming.config.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Classe listener du job associé à l'entité Examen qui notifie l'heure de début et l'heure de fin du job
 * @author Yohann
 * @since 1.0.x
 *
 */
public class ExamenListener implements JobExecutionListener {

	/**
	 *Méthode qui notifie le début du job lié à la lecture du fichier de données examens et à l'écriture dans la DB
	 *@param jobExecution Correspond au job lié à l'entité examen
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("******************               JOB EXAMEN START          ************************");
		System.out.println("Heure de début   :     "+new Date());
	}

	/**
	 *Méthode qui notifie la fin du job lié à la lecture du fichier de données examens et à l'écriture dans la DB
	 *@param jobExecution Correspond au job lié à l'entité examen
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Fin du job    :      "+new Date()+"    ; Statut du job    :     "+jobExecution.getStatus());
		System.out.println("******************              JOB EXAMEN END           ************************");
	}

}
