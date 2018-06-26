package fr.cs.simergy.humanresources;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import fr.cs.simergy.coreed.MessageBox;
import fr.cs.simergy.simulation.Link;
/**
 * This class represents a physician who is in charge of overseeing patients
 * throughout their staying in the ED. He visits a patient, takes exams' 
 * decisions and emits results.
 */
public class Physician extends Link {
	
	
	//Attributes
	/**
	 * The physician's name
	 */
	public String name;
	/**
	 * The physician's surname
	 */
	public String surname;
	/**
	 * The physician's unique ID
	 */
	public final long physicianID = ThreadLocalRandom.current().nextLong(100000000);
	/**
	 * The list of patients that are currently overseen by the physician
	 */
	public ArrayList<Patient> patientListOverseen = new ArrayList<Patient>();
	/**
	 * The list of patients that the physician has already treated
	 */
	public ArrayList<Patient> patientListTreated = new ArrayList<Patient>();
	/**
	 * The physician's message box
	 */
	public MessageBox messageBox = new MessageBox();
	/**
	 * The current time
	 */
	private int time;
	/**
	 * The cost of a consultation with a physician
	 */
	private double consultationCost = 100;
	
	//Constructor
	public Physician(){
		this.name = "Physician" + this.physicianID;
		this.surname = "Physician" + this.physicianID;
	}
	/**
	 * Physician Constructor
	 * @param name
	 * The physicians name
	 * @param surname
	 * The physicians surname
	 * @see Physician#name
	 * @see Physician#surname
	 */
	public Physician(String name, String surname){
		this.name = name;
		this.surname = surname;
	}
	
	// Methods
	/**
	 * Returns the specific Physician event time
	 * @return time
	 */
	public int getTime(){
		return this.time;
	}
	/**
	 * Sets the time for a specific Physician event
	 * @param p
	 * The new time
	 * @see Physician#time
	 */
	public void setTime(int p){
		this.time = p;
	}
	/**
	 * Returns the Physician's name
	 * @return name
	 * @see Physician#name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Returns the Physician's ID
	 * @return id
	 */
	public long getPhysicianID(){
		return this.physicianID;
	}
	
	/**
	 * Adds a new patient to the list of patients who are currenty overseen
	 * @param patient added to the physician list
	 * @see Patient
	 */
	public void startNewPatient(Patient patient){
		this.patientListOverseen.add(patient);
	}
	/**
	 * Remove a released patient from the currently overseen list
	 * and puts him in the treated list
	 * @param patient who has been released
	 * @see Patient
	 */
	public void setReleasedPatient(Patient patient){
		this.patientListOverseen.remove(patient);
		this.patientListTreated.add(patient);
	}
	/**
	 * Creates a random outcome for the patient 
	 * @return the exam that has been prescribed for the patient
	 */
	public static String prescribeExam(){
		Random r = new Random();
		float chance = r.nextFloat();
		String s;
		if (chance >= 0.00f && chance <= 0.35f)
			s = "nothing";
		else if (chance >= 0.35f && chance <= 0.55f)
			s = "radio";
		else if (chance >= 0.55f && chance <= 0.95f)
			s = "blood";
		else
			s = "mri";
		return s;
	}
	/**
	 * Emits a verdict on a patient's situation 
	 * regarding given probability distributions
	 * @param patient who gets the verdict
	 * @return the verdict 
	 */
	public static String emitVerdict(Patient patient){
		Random r = new Random();
		float chance = r.nextFloat();
		String s = "nothing";
		if (patient.getNumberOfTests() < 2){
			if (patient.getSeverityLevel() == 1){
				if (chance >= 0.00f && chance <= 0.10f){
					s = prescribeExam();
				}
			}
			else if (patient.getSeverityLevel() == 2){
				if (chance >= 0.00f && chance <= 0.05f){
					s = prescribeExam();
				}
			}
			else if (patient.getSeverityLevel() == 3){
				if (chance >= 0.00f && chance <= 0.001f){
					s = prescribeExam();
				}
			}
		}
		return s;
	}
	
	/**
	 * Puts a new message in the Physician's messagebox
	 * @param m
	 */
	/*public void receiveNewMessage(Message m){
		messageBox.append(m);
	}*/
	/**
	 * Displays a message from the Physician's messagebox
	 */
	/*public String displayMessage(Message m){
		messageBox.display(m);
	}*/
	
	@Override
	public String toString(){
		return "Physician Name : " + this.name + " ___ Surname : "+ this.surname + " ___ ID : " + this.physicianID;
	}
	/**
	 * Returns a physician consultation cost
	 * @return consultation cost
	 */
	public double getConsultationCost() {
		return consultationCost;
	}
	/**
	 * Sets a constulation cost for a physician
	 * @param consultationCost of the physician
	 */
	public void setConsultationCost(double consultationCost) {
		this.consultationCost = consultationCost;
	}

	
}
