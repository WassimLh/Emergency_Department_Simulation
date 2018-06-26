package fr.cs.simergy.simulation;

import fr.cs.simergy.humanresources.Patient;
/**
 * Defines the strategies to sort patients
 * in their queues
 */
public interface SortingPatients {
	/**
	 * Gets the priority patient in his queue
	 * @param s queue
	 * @return priority patient
	 */
	public Patient getPriorityPatient(Head s);
}
