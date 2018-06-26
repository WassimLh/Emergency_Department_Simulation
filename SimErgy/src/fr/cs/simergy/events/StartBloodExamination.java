package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the beginning of a Blood Examination
 */
public class StartBloodExamination extends Event {
	
	//Attributes
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The patient who will start a blood examination
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The room where the blood examination will take place
	 * @see Room
	 */
	private Room room;
	
	//Constructor
	/**
	 * The StartBloodExamination constructor
	 * @param sim
	 */
	public StartBloodExamination(EmergencySimulation sim){
		this.sim = sim;
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
	 * Gets the first patient on the waiting queue for a blood test, 
	 * Gets the first empty blood test room, reports the event, 
	 * schedules a new EndBloodExamination event.
	 */
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForBloodTest());
		this.patient = patient;
		patient.out();
		
		Room room = (Room) this.sim.getEmptyBloodRooms().first();
		this.room = room;
		room.out();
		
		report();
		
		new EndBloodExamination(this.sim, this.patient, this.room).schedule(time() + this.sim.getBloodDist().getSample());
	}
	/**
	 * Prints the report of the "start blood examination" event that took place
	 */
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Start Blood Examination _____ Patient ID : " + this.patient.getPatientID());
	}

	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	/**
	 * Returns the patient who will start a blood test
	 * @return patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * Returns the room where the blood examination will take place
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}

	
}
