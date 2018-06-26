package fr.cs.simergy.events;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * An event that indicates the arrival of an L4 patient to the ED
 */
public class PatientArrivalL4 extends PatientArrival {
	/**
	 * @see PatientArrival#PatientArrival(EmergencySimulation)
	 */
	public PatientArrivalL4(EmergencySimulation sim){
		super(sim);
	}
	/**
	 * @see PatientArrival#actions()
	 */
	public void actions(){
		if (time() <= getSim().getSimPeriod()){
			setPatient(new Patient(4, time()));
			getPatient().into(getSim().getPatientsWaitingForRegistration());
			getSim().getPatientsInSimulation().add(getPatient());
			report();
			
			getSim().updateEventQueue();
			
			new PatientArrivalL4(getSim()).schedule(time() + getSim().getL4Dist().getSample());
		}
	}
}
