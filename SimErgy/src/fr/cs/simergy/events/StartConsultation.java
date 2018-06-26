package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * A consultation is a necessary step in the ED process.
 * It is made by a physician on a patient.
 */

public class StartConsultation extends Event {
	
	// Attributes
	/**
	 * The physician who consults
	 */
	private Physician physician;
	/**
	 * The patient who is consulted by a physician
	 */
	private Patient patient;
	/**
	 * The room where the consultation is made 
	 */
	private Room room;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	
	//Constructor
	/**
	 * The StartConsultation constructor
	 * @param sim
	 */
	public StartConsultation(EmergencySimulation sim){
		this.sim = sim;
	}
	
	//Methods
	/**
	 * Returns the patient who is going to get consulted
	 * @return patient
	 */
	public Patient getPatient(){
		return this.patient;
	}
	/**
	 * Returns the physician who will make the consultation
	 * @return physician
	 */
	public Physician getPhysician(){
		return this.physician;
	}
	/**
	 * Returns the room where the consultation will take place
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
	 * Gets the first patient on the waiting queue for a consultation, 
	 * Gets the first idle physician, reports the event, 
	 * schedules a new EndConsultation event.
	 */
	@Override
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForConsultation());
		this.patient = patient;
		patient.out();
		
		
		Physician physician = (Physician) this.sim.getIdlePhysicians().first();
		this.physician = physician;
		this.physician.startNewPatient(this.patient);
		physician.out();
		
		if (patient.getNumberOfTests()<1){
			if (this.patient.getSeverityLevel() == 1){
				this.sim.getDTDT_L1().add(time() - this.patient.getArrivalTime());
			}
			else if (this.patient.getSeverityLevel() == 2){
				this.sim.getDTDT_L2().add(time() - this.patient.getArrivalTime());
			}
			else if (this.patient.getSeverityLevel() == 3){
				this.sim.getDTDT_L3().add(time() - this.patient.getArrivalTime());
			}
			else if (this.patient.getSeverityLevel() == 4){
				this.sim.getDTDT_L4().add(time() - this.patient.getArrivalTime());
			}
			else if (this.patient.getSeverityLevel() == 5){
				this.sim.getDTDT_L5().add(time() - this.patient.getArrivalTime());
			}
		}
		
		report();
		
		new EndConsultation(this.sim, this.patient, this.physician).schedule(time() + this.sim.getConsultationDist().getSample());
	}
	/**
	 * Prints the report of the "start consultation" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Start Consultation _____ Patient ID : " + this.patient.getPatientID());
	}

}
