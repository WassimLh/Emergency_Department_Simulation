package fr.cs.simergy.humanresources;
import java.util.concurrent.ThreadLocalRandom;

import fr.cs.simergy.simulation.Link;
/**
 * A transporter works in the ED to register patients and transport
 * them to BoxRooms or ShockRooms depending on their severity level
 */
public class Transporter extends Link {

	
	// Attributes
	/**
	 * The transporter's name
	 */
	public String name;
	/**
	 * The transporter's surname
	 */
	public String surname;
	/**
	 * The transporter's unique ID
	 */
	public final long transporterID = ThreadLocalRandom.current().nextLong(100000000);
	
	//Constructor
	public Transporter(){
		this.name = "Transporter" + this.transporterID;
		this.surname = "Transporter" + this.transporterID;
	}
	/**
	 * Transporter's constructor
	 * @param name The transporter's name
	 * @param surname The transporter's surname
	 */
	public Transporter(String name, String surname){
		this.name = name;
		this.surname = surname;
	}
	
	//Methods
	/**
	 * Returns the transporter's name
	 * @return Transporter name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Return's the transporter's id
	 * @return Transporter id
	 */
	public long getTransporterID(){
		return this.transporterID;
	}
	
	@Override
	public String toString(){
		return "transporter Name : " + this.name + " ___ Surname : "+ this.surname + " ___ ID : " + this.transporterID;
	}

}
