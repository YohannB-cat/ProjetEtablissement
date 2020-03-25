package com.fr.adaming.config.listener;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * Classe listener du job associé à l'entité Examen qui notifie l'heure de début et l'heure de fin du job
 * @author Yohann
 * @since 1.0.x
 *
 */
public class NoteListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("******************               STEP NOTE START          ************************");
		System.out.println("Heure de début   :     "+new Date());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("******************              STEP NOTE END           ************************");
		return stepExecution.getExitStatus();
	}

}