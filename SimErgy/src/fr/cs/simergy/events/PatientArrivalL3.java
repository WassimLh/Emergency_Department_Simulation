package fr.cs.simergy.events;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * An event that indicates the arrival of an L3 patient to the ED
 */
public class PatientArrivalL3 extends PatientArrival {
	/**
	 * @see PatientArrival#PatientArrival(EmergencySimulation)
	 */
	public PatientArrivalL3(EmergencySimulation sim){
		super(sim);
	}
	/**
	 * @see PatientArrival#actions()
	 */
	public void actions(){
		if (time() <= getSim().getSimPeriod()){
			setPatient(new Patient(3, time()));
			getPatient().into(getSim().getPatientsWaitingForRegistration());
			getSim().getPatientsInSimulation().add(getPatient());
			report();
			
			getSim().updateEventQueue();
			
			new PatientArrivalL3(getSim()).schedule(time() + getSim().getL3Dist().getSample());
		}
	}
}
