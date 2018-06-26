package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the end of a Radio Examination
 */
public class EndRadioExamination extends Event {
	
	//Attributes
	/**
	 * The patient who finished a Radio examination
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The room where the Radio examination took place
	 * @see Room
	 */
	private Room room;
	//Constructor
	/**
	 * The EndRadioExamination event constructor
	 * @param sim
	 * @param patient
	 * @param room
	 */
	public EndRadioExamination(EmergencySimulation sim, Patient patient, Room room){
		this.sim = sim;
		this.patient = patient;
		this.room = room;
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
	 * Makes the Radio room where the test took place empty, reports the event, 
	 * updates the simulation event queue and releases the patient
	 */
	public void actions(){
		room.into(sim.getEmptyRadioRooms());
		
		report();
		
		this.patient.setCost(this.patient.getCost() + this.room.getCost());
		
		String exam = Physician.emitVerdict(patient);
		patient.setExamRequired(exam);
		
		if (patient.getSeverityLevel() == 4 || patient.getSeverityLevel() == 5 || exam.equals("nothing"))
			new Release(this.sim, this.patient).schedule(time());
		
		else{
			patient.into(this.sim.getPatientsWaitingForTransportation());
		}
		this.sim.updateEventQueue();
	}
	/**
	 * Prints the report of the "end radio examination" event that took place
	 */
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ End Radio Examination _____ Patient ID : " + this.patient.getPatientID());
	}
	
	/**
	 * Returns the patient that had a Radio examination
	 * @return patient
	 */	
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}

	/**
	 * Returns the room where the Radio examination took place
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}
	
}
