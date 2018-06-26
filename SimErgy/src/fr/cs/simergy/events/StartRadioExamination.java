package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the beginning of a Radio Examination
 */
public class StartRadioExamination extends Event {
	
	//Attributes
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The patient who will start a Radio examination
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The room where the Radio examination will take place
	 * @see Room
	 */
	private Room room;
	
	//Constructor
	/**
	 * The StartRadioExamination constructor
	 * @param sim
	 */
	public StartRadioExamination(EmergencySimulation sim){
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
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	/**
	 * Gets the first patient on the waiting queue for a Radio test, 
	 * Gets the first empty Radio Room, reports the event, 
	 * schedules a new EndRadioExamination event.
	 */
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForRadioTest());
		this.patient = patient;
		patient.out();
		
		Room room = (Room) this.sim.getEmptyRadioRooms().first();
		this.room = room;
		room.out();
		
		report();
		
		new EndRadioExamination(this.sim, this.patient, this.room).schedule(time() +  this.sim.getRadioDist().getSample());
	}
	/**
	 * Prints the report of the "start radio examination" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Start Radio Examination _____ Patient ID : " + this.patient.getPatientID());
	}
}
