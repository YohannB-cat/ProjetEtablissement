package com.fr.adaming.config.listener;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * Classe Listener pour l'entité Matière
 * Implémente StepListener et fait partie du job de MAJ de DB
 * @author maxime
 *
 */
public class MatiereListener implements StepExecutionListener {

	/**
	 * Méthode affichant un message dans la console avant l'execution du step
	 * Permet d'avoir un affichage clair dans la console
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("-------------> Le step commence");
	}

	/**
	 *Méthode affichant un message dans la console après l'exdcution du step
	 *Permet d'avoir un affichage clair dans la console
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("########## Step terminé " + new Date() + " ###########");

		if (stepExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("-----------> job Success");
		} else if (stepExecution.getStatus() == BatchStatus.FAILED) {
			System.out.println("xxxxxxxxxxxxxxx Step Failed xxxxxxxxxxxxx");
		}
		return stepExecution.getExitStatus();
	}

}
