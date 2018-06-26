package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An Installation is a necessary step in the ED process.
 * An L1 or L2 patient is installed by a nurse in a Shock Room
 * to get visited.
 */
public class StartShockInstallation extends Event {

	
	//Attributes
	/**
	 * The nurse who installs the patient
	 */
	private Nurse nurse;
	/**
	 * The patient who gets installed
	 */
	private Patient patient;
	/**
	 * The room where the patient is installed
	 */
	private Room room;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	
	// Constructor
	/**
	 * The StartInstallation Constructor
	 * @param sim
	 */
	public StartShockInstallation(EmergencySimulation sim){
		this.sim = sim;
	}
	//Methods
	/**
	 * Returns the patient who will get installed
	 * @return patient
	 */
	public Patient getPatient(){
		return this.patient;
	}
	/**
	 * Returns the nurse who will install the patient
	 * @return nurse
	 */
	public Nurse getNurse(){
		return this.nurse;
	}
	/**
	 * Returns the room where the installation will take place
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
	 * Returns the ED simulation
	 * @return simulation
	 */	
	public EmergencySimulation getSim() {
		return sim;
	}

	/**
	 * Gets the first patient on the waiting queue for an installation, 
	 * Gets the first idle nurse, Gets the first empty installation room, reports the event, 
	 * schedules an EndInstallation event at time() + 2.
	 */
	@Override
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForShockInstallation());
		this.patient = patient;
		patient.out();
		
		Nurse nurse = (Nurse) this.sim.getIdleNurses().first();
		this.nurse = nurse;
		nurse.out();
		
		Room room = (Room) this.sim.getEmptyShockRooms().first();
		this.room = room;
		room.out();
		
		report();
		
		new EndInstallation(this.sim, this.patient, this.nurse, this.room).schedule(time() + 2);
		
	}
	/**
	 * Prints the report of the "start installation" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Start Installation _____ Patient ID : " + this.patient.getPatientID());
	}
	
}
