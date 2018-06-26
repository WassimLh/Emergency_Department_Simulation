package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Transporter;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * A Transportation is a necessary step in the ED process.
 * A patient is transported by a transporter between different
 * ED services.
 */
public class StartTransportation extends Event {
	
	
	//Attributes
	/**
	 * The transporter who transports the patient
	 */
	private Transporter transporter;
	/**
	 * The patient who is transported
	 */
	private Patient patient;
	/**
	 * The room where the patient is transported to
	 */
	private Room room;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	
	//Constructor
	/**
	 * The StartTransportation constructor
	 * @param sim
	 */
	public StartTransportation(EmergencySimulation sim){
		this.sim = sim;
		}
	
	//Methods
	/**
	 * Returns the patient who will get transported
	 * @return patient
	 */
	public Patient getPatient(){
		return this.patient;
	}
	/**
	 * Returns the room where the patient will be transported to
	 * @return room
	 */
	public Room getRoom(){
		return this.room;
	}
	/**
	 * Returns the transporters who will transport the patient
	 * @return nurse
	 */
	public Transporter getTransporter(){
		return this.transporter;
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
	 * Gets the first patient on the waiting queue for Transportation, 
	 * Gets the first idle Transporter, reports the event, 
	 * schedules an EndTransportation event at time() + 5.
	 */
	@Override
	public void actions(){
		Patient patient = this.sim.getSort().getPriorityPatient(this.sim.getPatientsWaitingForTransportation());
		this.patient = patient;
		patient.out();
		
		Transporter transporter = (Transporter) this.sim.getIdleTransporters().first();
		this.transporter = transporter;
		transporter.out();
		
		patient.setNumberOfTests(patient.getNumberOfTests() + 1);
		
		report();
		
		new EndTransportation(this.sim, this.patient, this.transporter).schedule(time() + 5);
	}
	/**
	 * Prints the report of the "start transportation" event that took place
	 */
	@Override
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ StartTransportation _____ Patient ID : " + this.patient.getPatientID());
	}
}
