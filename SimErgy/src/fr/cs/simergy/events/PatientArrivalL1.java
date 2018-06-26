package fr.cs.simergy.events;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * An event that indicates the arrival of an L1 patient to the ED
 */
public class PatientArrivalL1 extends PatientArrival {
	/**
	 * @see PatientArrival#PatientArrival(EmergencySimulation)
	 */
	public PatientArrivalL1(EmergencySimulation sim) {
		super(sim);
	}
	/**
	 * @see PatientArrival#actions()
	 */
	public void actions() {
		if (time() <= getSim().getSimPeriod()) {
			setPatient(new Patient(1, time()));
			getSim().getPatientsInSimulation().add(getPatient());
			
			getPatient().into(getSim().getPatientsWaitingForRegistration());
			
			report();

			getSim().updateEventQueue();

			new PatientArrivalL1(getSim()).schedule(time() + getSim().getL1Dist().getSample());
		}
	}
}
