package fr.cs.simergy.events;

import java.text.DecimalFormat;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * A Release is the last step in the ED process.
 * A patient is released when he finishes all the tests and visits.
 */
public class Release extends Event {
	
	//Attributes
	/**
	 * The patient who is released from the ED
	 * @see Patient
	 */
	private Patient patient;
	/**
	 * The time when the patient is released from the ED
	 */
	private int releaseTime;
	/**
	 * The ED simulation where this event takes place
	 * @see EmergencySimulation
	 */
	private EmergencySimulation sim;
	
	//Constructor
	/**
	 * The Release event constructor
	 * @param sim
	 * @param patient
	 */
	public Release(EmergencySimulation sim, Patient patient){
		this.sim = sim;
		this.patient = patient;
	}
	
	//Methods
	/**
	 * Reports the patient's release
	 */
	@Override
	public void actions(){
		if (this.patient.getSeverityLevel() == 1){
			this.sim.getLOS_L1().add(time() - this.patient.getArrivalTime());
		}
		else if (this.patient.getSeverityLevel() == 2){
			this.sim.getLOS_L2().add(time() - this.patient.getArrivalTime());
		}
		else if (this.patient.getSeverityLevel() == 3){
			this.sim.getLOS_L3().add(time() - this.patient.getArrivalTime());
		}
		else if (this.patient.getSeverityLevel() == 4){
			this.sim.getLOS_L4().add(time() - this.patient.getArrivalTime());
		}
		else if (this.patient.getSeverityLevel() == 5){
			this.sim.getLOS_L5().add(time() - this.patient.getArrivalTime());
		}
		report();
	}
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public int getSeverityLevel(){
		return this.patient.getSeverityLevel();
	}
	/**
	 * Prints the report of the "release" event that took place
	 */
	public void report(){
		System.out.println("t = " + new DecimalFormat("#.##").format(time()) + " _____ Release _____ Patient ID : " 
					+ this.patient.getPatientID() + " _____ Cost : " + this.patient.computePatientCharges());
	}

	//Getters and Setters
	/**
	 * Returns the patient who got released
	 * @return patient
	 */
	public Patient getPatient(){
		return this.patient;
	}
	/**
	 * Returns the time at which the patient got released
	 * @return releaseTime
	 */
	public int getReleaseTime() {
		return releaseTime;
	}
	/**
	 * Returns the ED simulation
	 * @return simulation
	 */
	public EmergencySimulation getSim() {
		return sim;
	}

}
