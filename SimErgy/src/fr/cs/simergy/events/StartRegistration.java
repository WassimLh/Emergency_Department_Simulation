package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * A Registration is the first step in the ED process.
 * A patient is registered by a nurse once he arrives to the ED.
 */

public class StartRegistration extends Event {
	
	//Attributes
	
	/**
	 * The nurse who registers the patient
	 */
	private Nurse nurse;
	/**
	 * The patient who is registered by the nurse
	 */
	private Patient patient;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	
	//Constructor
	/**
	 * The StartRegistration constructor
	 * @param sim
	 */
	public StartRegistration(EmergencySimulation sim){
		this.sim = sim;
	}
	//Methods
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public Patient getPatient(){
		return this.patient;
	}
	/**
	 * Returns the nurse who will register the patient
	 * @return nurse
	 */
	public Nurse getNurse(){
		return this.nurse;
	}
	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public int getSeverityLevel(){
		return this.patient.getSeverityLevel();
	}
	/**
	 * Gets the first patient on the waiting queue for Registration, 
	 * Gets the first idle Nurse, reports the event, 
	 * schedules an EndRegistration event at time() + 1.
	 */
	@Override
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForRegistration());
		this.patient = patient;
		patient.out();
		
		Nurse nurse = (Nurse) this.sim.getIdleNurses().first();
		this.nurse = nurse;
		nurse.out();
					
		report();
			
		new EndRegistration(this.sim, this.patient, this.nurse).schedule(time() + 1);
	}
	/**
	 * Prints the report of the "start registration" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Start Registration _____ Patient ID : " + this.patient.getPatientID());
	}
}
