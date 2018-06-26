package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Transporter;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * An event that indicates the end of a transportation
 */
public class EndTransportation extends Event {
	
	//Attributes
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	/**
	 * The transporter who transported the patient
	 * @see Nurse
	 */
	private Transporter transporter;
	/**
	 * The patient who got transported
	 * @see Patient
	 */
	private Patient patient; 
	
	//Constructor
	/**
	 * The EndTransportation constructor
	 * @param sim
	 * @param patient
	 * @param transporter
	 */
	public EndTransportation(EmergencySimulation sim, Patient patient, Transporter transporter){
		this.sim = sim;
		this.patient = patient;
		this.transporter = transporter;
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
	 * Makes the transporter idle, Puts the patient in the relevant waiting queue depending on his required exam, 
	 * reports the event and updates the event queue
	 */
	@Override
	public void actions(){
		transporter.into(sim.getIdleTransporters());
		
		String exam = patient.getExamRequired();
		if (exam.contentEquals("radio")){
			patient.into(sim.getPatientsWaitingForRadioTest());
		}
		else if (exam.contentEquals("blood")){
			patient.into(sim.getPatientsWaitingForBloodTest());
		}
			
		else if (exam.contentEquals("mri")){
			patient.into(sim.getPatientsWaitingForMRITest());
		}
		
		report();
		

		this.sim.updateEventQueue();
		
	}
	/**
	 * Prints the report of the "end transportation" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) +  " _____ End Transportation _____ Patient ID :" + this.patient.getPatientID());
	}
	
	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}
	/**
	 * Returns the transporters who transported the patient
	 * @return nurse
	 */
	public Transporter getTransporter() {
		return transporter;
	}

	/**
	 * Returns the patient who got transported
	 * @return patient
	 */
	public Patient getPatient() {
		return patient;
	}

}
