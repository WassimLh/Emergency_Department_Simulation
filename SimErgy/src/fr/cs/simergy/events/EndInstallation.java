package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the end of an Installation
 */	
public class EndInstallation extends Event {
	
	//Attributes
	/**
	 * The nurse who installs the patient
	 * @see Nurse
	 */
	private Nurse nurse;
	/**
	 * The patient that gets installed
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The room where the patient is installed
	 * @see Room
	 */
	private Room room;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	//Constructor
	/**
	 * The EndInstallation event constructor
	 * @param sim
	 * @param patient
	 * @param nurse
	 * @param room
	 */
	public EndInstallation(EmergencySimulation sim, Patient patient, Nurse nurse, Room room){
		this.sim = sim;
		this.patient = patient;
		this.nurse = nurse;
		this.room = room;
	}
	//Methods
	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	/**
	 * Returns the patient who got installed
	 * @return patient
	 */
	public Patient getPatient(){
		return this.patient;
	}
	/**
	 * Returns the nurse who installed the patient
	 * @return nurse
	 */
	public Nurse getNurse(){
		return this.nurse;
	}
	/**
	 * Returns the room where the blood examination took place
	 * @return room
	 */
	public Room getRoom(){
		return this.room;
	}
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public int getSeverityLevel(){
		return this.patient.getSeverityLevel();
	}
	/**
	 * Makes the nurse idle, Puts the patient in the waiting queue for consultation, changes the patient location,
	 * reports the event and updates the simulation event queue
	 */
	@Override
	public void actions(){
		nurse.into(sim.getIdleNurses());
		
		patient.into(sim.getPatientsWaitingForConsultation());
		
		patient.changeLocation(this.room);
		
		report();
		
		this.sim.updateEventQueue();
		
	}
	/**
	 * Prints the report of the "end installation" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ End Installation _____ Patient ID : " + this.patient.getPatientID());
	}
}
