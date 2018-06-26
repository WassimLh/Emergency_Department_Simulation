package fr.cs.simergy.events;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * An event that indicates the arrival of an L5 patient to the ED
 */
public class PatientArrivalL5 extends PatientArrival {
	/**
	 * @see PatientArrival#PatientArrival(EmergencySimulation)
	 */
	public PatientArrivalL5(EmergencySimulation sim){
		super(sim);
	}
	/**
	 * @see PatientArrival#actions()
	 */
	public void actions(){
		if (time() <= getSim().getSimPeriod()){
			setPatient(new Patient(5, time()));
			getPatient().into(getSim().getPatientsWaitingForRegistration());
			getSim().getPatientsInSimulation().add(getPatient());
			report();
			
			getSim().updateEventQueue();
			
			new PatientArrivalL5(getSim()).schedule(time() + getSim().getL5Dist().getSample());
		}
	}
}
