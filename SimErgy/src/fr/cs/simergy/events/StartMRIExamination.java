package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the beginning of an MRI Examination
 */
public class StartMRIExamination extends Event {
	
	//Attributes
	/**
	 * The patient who will start an MRI examination
	 * @see Patient
	 */
	private EmergencySimulation sim;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private Patient patient;
	/**
	 * The room where the MRI examination will take place
	 * @see Room
	 */
	private Room room;
	
	//Constructor
	/**
	 * The StartMRIExamination constructor
	 * @param sim
	 */
	public StartMRIExamination(EmergencySimulation sim){
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
	 * Gets the first patient on the waiting queue for an MRI test, 
	 * Gets the first empty MRI Room, reports the event, 
	 * schedules a new EndMRIExamination event.
	 */
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForMRITest());
		this.patient = patient;
		patient.out();
		
		Room room = (Room) this.sim.getEmptyMRIRooms().first();
		this.room = room;
		room.out();
		
		report();
		
		new EndMRIExamination(this.sim, this.patient, this.room).schedule(time() + this.sim.getMriDist().getSample());
	}
	/**
	 * Prints the report of the "start MRI examination" event that took place
	 */
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Start MRI Examination _____ Patient ID : " + this.patient.getPatientID());
	}
}
