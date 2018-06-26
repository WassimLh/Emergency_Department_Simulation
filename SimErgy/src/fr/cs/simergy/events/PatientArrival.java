package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the arrival of a patient to the ED
 */
public class PatientArrival extends Event {
	
	//Attributes
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The patient who arrives to the ED
	 * @see Patient
	 */
	private Patient patient;
	//Constructor
	/**
	 * The main PatientArrival constructor
	 * @param sim The ED Simulation
	 * @param sevLevel The patient's severity level
	 * param arrivalTime the patient's arrival time
	 */
	public PatientArrival(EmergencySimulation sim, int sevLevel, int arrivalTime){
		this.sim = sim;
		this.patient = new Patient(sevLevel, arrivalTime);
	}
	/**
	 * A PatientArrival constructor with a given patient
	 * @param sim The ED Simulation
	 * @param patient the patient on the arrival
	 */
	public PatientArrival(EmergencySimulation sim, Patient patient){
		this.sim = sim;
		this.patient = patient;
	}
	/**
	 * A PatientArrival constructor with only the simulation parameter
	 * @param sim The ED Simulation
	 */
	public PatientArrival(EmergencySimulation sim){
		this.sim = sim;
	}
	
	//Methods
	/**
	 * Puts the patient in the waiting queue for registration, 
	 * reports the event and updates the event queue.
	 */
	@Override
	public void actions(){
		if (time() <= sim.getSimPeriod()){
			patient.into(sim.getPatientsWaitingForRegistration());
			report();
			
			this.sim.updateEventQueue();
		}
	}
	
	/**
	 * Prints the report of the "patient arrival" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Arrival _____ Patient ID : " + this.patient.getPatientID() + 
				" _____ SeverityLevel : L" + this.patient.getSeverityLevel());
	}
	
	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	
	/**
	 * Returns the patient who arrived
	 * @return patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * Sets a patient on the arrival
	 */
	public void setPatient(Patient patient){
		this.patient = patient;
	}
	
}
