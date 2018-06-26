package fr.cs.simergy.humanresources;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import fr.cs.simergy.coreed.HealthInsurance;
import fr.cs.simergy.coreed.NoInsurance;
import fr.cs.simergy.materialresources.Room;
import fr.cs.simergy.simulation.Event;
import fr.cs.simergy.simulation.Link;
/**
 * A Patient is the ED's client. 
 * This class contains patient's identity 
 * information and methods about his evolution 
 * through the ED workflow.  
 */
public class Patient extends Link {
	
	
	//Attributes
	/**
	 * The patient's name
	 */
	private String name;
	/**
	 * The patient's surname
	 */
	private String surname;
	/**
	 * The patient's unique ID once 
	 * he is registered in the ED.
	 */
	private final long patientID = ThreadLocalRandom.current().nextLong(100000000);
	/**
	 * Information about the eventual patient's 
	 * Health insurance
	 */
	private HealthInsurance insurance = NoInsurance.getInstance();
	/**
	 * The time when the patient first arrives to the ED
	 */
	private double arrivalTime;
	/**
	 * The patient's state severity level,
	 * which increases from L5 to L1.
	 */
	private int severityLevel;
	/**
	 * The patient's location within the ED.
	 */
	private Room location;
	/**
	 * The history of the process that the patient
	 * went through in the ED.
	 */
	private ArrayList<Event> history = new ArrayList<Event>();
	
	/**
	 * The examination that the patient needs to undergo
	 */
	private String examRequired;
	/**
	 * The physician who oversees the patient
	 */
	private Physician associatedPhysician;
	
	private double cost;
	
	private int numberOfTests = 0;
	
	
	// Constructor
	/**
	 * Primary Patient's constructor with only the severity level as a known parameter
	 * @param n
	 * The patient's severity level
	 * @see Patient#severityLevel
	 */
	public Patient(int n, double d){
		this.severityLevel = n;
		this.arrivalTime = d;
		this.name = "Patient" + this.patientID;
		this.surname = "Patient" + this.patientID;
	}
	/**
	 * Complete Patient's constructor
	 * @param name The patient's name
	 * @param severityLevel The patient's severity level
	 * @param arrivalTime The patient's arrival time
	 * @see Patient#name
	 * @see Patient#surname
	 * @see Patient#arrivalTime
	 * @see Patient#severityLevel
	 */
	
	public Patient(String name,  int severityLevel, double arrivalTime){
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.severityLevel = severityLevel;
	}
	
	public Patient(String name, int severityLevel, double arrivalTime, HealthInsurance hi){
		this.name = name;
		this.severityLevel = severityLevel;
		this.arrivalTime = arrivalTime;
		this.insurance = hi;
	}
	
	public Patient(String name, String surname, double arrivalTime, int severityLevel){
		this.name = name;
		this.surname = surname;
		this.arrivalTime = arrivalTime;
		this.severityLevel = severityLevel;
	}
	
	public Patient(String name, String surname, int severityLevel, double arrivalTime, HealthInsurance hi){
		this.name = name;
		this.surname = surname;
		this.severityLevel = severityLevel;
		this.arrivalTime = arrivalTime;
		this.severityLevel = severityLevel;
	}
	
	// Methods
	/**
	 * Sets a new arrival time for the patient
	 * @param n The arrival time
	 */
	public void setArrivalTime(int n){
		this.arrivalTime = n;
	}
	
	/**
	 * Changes the patient's location through the process
	 * @param room
	 * @see Room
	 */
	public void changeLocation(Room room){
		this.location = room;
	}
	/**
	 * Returns the patient's location
	 * @return room
	 */
	public Room getLocation(){
		return this.location;
	}
	/**
	 * Returns the patient's severity level
	 * @return severity level
	 */
	public int getSeverityLevel(){
		return this.severityLevel;
	}
	/**
	 * Updates the patient's history through the ED when a new event occurs
	 * @param observable An event that occurred to the patient
	 */
	public void updatePatientHistory(Event observable){
		// observer pattern
		this.history.add(observable);
	}
	/**
	 * Returns the patient's history through the ED
	 * @return to String history
	 */
	public String getHistory(){
		return this.history.toString();
	}
	/**
	 * Return's the patient's arrival time
	 * @return arrivalTime
	 */
	public double getArrivalTime(){
		return this.arrivalTime;
	}
	/**
	 * Returns the patient's id
	 * @return id
	 */
	public long getPatientID(){
		return this.patientID;
	}
	/**
	 * Returns the physician who is in charge of this patient
	 * @return associatedPhysician
	 */
	public Physician getAssociatedPhysician(){
		return this.associatedPhysician;
	}
	/**
	 * Changes the patient's associated physician
	 * @param physician
	 */
	public void setAssociatedPhysician(Physician physician){
		this.associatedPhysician = physician;
	}
	/**
	 * Returns the exam that is required for this patient
	 * @return string exam	
	 */
	public String getExamRequired(){
		return this.examRequired;
	}
	/**
	 * Changes the patient's required exam
	 * @param s The required exam
	 */
	public void setExamRequired(String s){
		this.examRequired = s;
	}
	/**
	 * Calculates the charges that the patient needs to pay for the ED
	 * @return
	 */
	public double computePatientCharges(){
		return this.insurance.getFinalCost(this);
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString(){
		return "Patient Name : " + this.name + " ___ Surname : "+ this.surname + " ___ ID : " + this.patientID;
	}
	public String getName() {
		return name;
	}
	public HealthInsurance getInsurance() {
		return insurance;
	}
	public void setInsurance(HealthInsurance insurance) {
		this.insurance = insurance;
	}
	public int getNumberOfTests() {
		return numberOfTests;
	}
	public void setNumberOfTests(int numberOfTests) {
		this.numberOfTests = numberOfTests;
	}
}
