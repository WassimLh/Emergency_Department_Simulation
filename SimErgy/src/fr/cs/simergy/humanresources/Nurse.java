package fr.cs.simergy.humanresources;

import java.util.concurrent.ThreadLocalRandom;

import fr.cs.simergy.simulation.Link;
/**
 * A Nurse works in the ED to register patients and transport
 * them to BoxRooms or ShockRooms depending on their severity level
 */
public class Nurse extends Link {
		
	// Attributes
	/**
	 * The nurse's name
	 */
	public String name;
	/**
	 * The nurse's surname
	 */
	public String surname;
	/**
	 * The nurse's unique ID
	 */
	public final long nurseID = ThreadLocalRandom.current().nextLong(100000000);
	
	//Constructor
	public Nurse(){
		this.name = "Nurse" + nurseID;
		this.surname = "Nurse" + nurseID;
	}
	/**
	 * Nurse's constructor
	 * @param name The nurse's name
	 * @param surname The nurse's surname
	 */
	public Nurse(String name, String surname){
		this.name = name;
		this.surname = surname;
	}
	
	//Methods
	/**
	 * Returns the nurse's name
	 * @return Nurse name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Returns the nurse's id	
	 * @return Nurse id
	 */
	public long getNurseID(){
		return this.nurseID;
	}
	
	@Override
	public String toString(){
		return "Nurse Name : " + this.name + " ___ Surname : "+ this.surname + " ___ ID : " + this.nurseID;
	}
	
}
