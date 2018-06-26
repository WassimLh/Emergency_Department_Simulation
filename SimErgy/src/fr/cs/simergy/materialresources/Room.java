package fr.cs.simergy.materialresources;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.Link;
/**
 * Rooms are the main ED's material resources
 */
public abstract class Room extends Link {
	//Attributes
	/**
	 * States if the room is occupied by a patient or not
	 */
	private String roomName;
	private boolean occupied;
	private Patient patient;
	private String state;
	private double cost;
	/**
	 * Room constructor with no attributes
	 */
	public Room(){
	}
	/**
	 * Room constructor with a given name
	 * @param s name of the room
	 */
	public Room(String s){
		this.roomName= s;
	}
	/**
	 * Room constructor with given name and cost
	 * @param s name of the room
	 * @param cost of the room's use  
	 */
	public Room(String s, double cost){
		this.roomName = s;
		this.cost = cost;
	}
	
	//Methods
	/**
	 * Makes this Blood Test Room occupied with the given patient
	 * @param patient
	 */
	public void setOccupied(Patient patient){
		this.patient = patient;
		this.occupied = true;
	}
	/**
	 * Returns the information whether the Blood Test Room is occupied or empty
	 * @return occupied
	 */
	public boolean isOccupied(){
		return this.occupied;
	}
	/**
	 * Gets the cost of the room
	 * @return cost of the room
	 */
	public double getCost(){
		return this.cost;
	}
	/**
	 * Sets the cost of the room
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Gets the name of the room
	 * @return name of the room
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * Sets the name of the room
	 * @param name the room's name
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * Gets the patient in the room if there is one (null if room is empty)
	 * @return patient in the room
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * Sets the patient in the room
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	/**
	 * Gets the occupation state of the room
	 * @return state of the room
	 */
	public String getState() {
		return state;
	}
	/**
	 * Sets the occupation state of the room
	 * @param state of the room
	 */
	public void setState(String state) {
		this.state = state;
	}
}
