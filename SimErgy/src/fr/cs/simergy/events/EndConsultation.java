package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the end of a consultation
 */
public class EndConsultation extends Event {
	
	//Attributes
	/**
	 * The patient who finished a consultation
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The physician who consulted on the patient
	 * @see Physician
	 */
	private Physician physician;
	/**
	 * The room where the consultation took place
	 * @see Room
	 */
	private Room room;
	
	//Constructor
	/**
	 * The end consultation event constructor
	 * @param sim
	 * @param patient
	 * @param physician
	 */
	public EndConsultation(EmergencySimulation sim, Patient patient, Physician physician){
		this.sim = sim;
		this.patient = patient;
		this.physician = physician;
	}
	//Methods
	/**
	 * Makes the physician idle, makes the consultation room empty, 
	 * reports the event, prescribes a further exam or releases the patients
	 * and updates the simulation event queue
	 */
	@Override
	public void actions(){
		physician.into(sim.getIdlePhysicians());
		
		this.room = this.patient.getLocation();
		room.into(sim.getEmptyInstallationRooms(patient));
		
		report();
		
		// What is the income ?!
		String exam = Physician.prescribeExam();
		if (exam.contentEquals("nothing")){
			new Release(this.sim, this.patient).schedule(time());
		}
		else{
			patient.into(sim.getPatientsWaitingForTransportation());
			patient.setExamRequired(exam);
		}
		
		this.patient.setCost(this.patient.getCost() + this.physician.getConsultationCost());
		
		this.sim.updateEventQueue();
	}
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public int getSeverityLevel(){
		return this.patient.getSeverityLevel();
	}
	/**
	 * Prints the report of the "end consultation" event that took place
	 */
	public void report(){
		System.out.println("t = "+ new DecimalFormat("#.##").format(time()) + " _____ End Consultation _____ Patient ID : " + this.patient.getPatientID() );
	}
	/**
	 * Returns the patient who got consulted
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
	 * Returns the physician who made the consultation
	 * @return physician
	 */
	public Physician getPhysician() {
		return physician;
	}
	/**
	 * Returns the room where the consultation took place
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}
	
}
