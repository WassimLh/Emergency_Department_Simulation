package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the end of an MRI Examination
 */
public class EndMRIExamination extends Event {
	
	//Attributes
	/**
	 * The patient who finished an MRI examination
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The room where the MRI examination took place
	 * @see Room
	 */
	private Room room;
	
	//Constructor
	/**
	 * The EndMRIExamination event constructor
	 * @param sim
	 * @param patient
	 * @param room
	 */
	public EndMRIExamination(EmergencySimulation sim, Patient patient, Room room){
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
	 * Makes the MRI room where the test took place empty, reports the event, 
	 * updates the simulation event queue and releases the patient
	 */
	public void actions(){
		room.into(sim.getEmptyMRIRooms());
		
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
	 * Prints the report of the "end MRI examination" event that took place
	 */
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ End MRI Examination _____ Patient ID : " + this.patient.getPatientID());
	}
	/**
	 * Returns the patient that had an MRI examination
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
	 * Returns the room where the MRI examination took place
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}
		
}
