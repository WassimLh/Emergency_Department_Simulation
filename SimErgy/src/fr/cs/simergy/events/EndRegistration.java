package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the end of a registration
 */
public class EndRegistration extends Event {
	
	//Attributes
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The patient who got registered
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The nurse who registered the patient
	 * @see Nurse
	 */
	private Nurse nurse;
	//Constructor
	/**
	 * The EndRegistration constructor
	 * @param sim
	 * @param patient
	 * @param nurse
	 */
	public EndRegistration(EmergencySimulation sim, Patient patient, Nurse nurse){
		this.sim = sim;
		this.patient = patient;
		this.nurse = nurse;
	}
	//Methods
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public int getSeverityLevel(){
		return this.patient.getSeverityLevel();
	}
	/**
	 * Makes the nurse idle, Puts the patient in the waiting queue for installation, 
	 * reports the event and updates the event queue
	 */
	public void actions(){
		nurse.into(sim.getIdleNurses());
		
		if(patient.getSeverityLevel() == 1 || patient.getSeverityLevel() == 2)
			patient.into(sim.getPatientsWaitingForShockInstallation());
		else
			patient.into(sim.getPatientsWaitingForBoxInstallation());
		
		report();
		
		this.sim.updateEventQueue();
		
	}
	/**
	 * Prints the report of the "end registration" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ End Registration _____ Patient ID : " + this.patient.getPatientID());
	}
	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	/**
	 * Returns the patient who got registered
	 * @return patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * Returns the nurse who registered the patient
	 * @return nurse
	 */
	public Nurse getNurse() {
		return nurse;
	}	
	
}
