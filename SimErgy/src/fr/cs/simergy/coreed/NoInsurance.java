package fr.cs.simergy.coreed;

import fr.cs.simergy.humanresources.Patient;
/**
 * Defines a specific health insurance for patients
 * who don't have any health insurance
 *
 */
public class NoInsurance implements HealthInsurance {
	
	private static NoInsurance instance = null;
	private double discount = 0;
	
	private NoInsurance(){};
	/**
	 * Method to implement the Singleton pattern for health insurance
	 */
	public static NoInsurance getInstance(){
		if (instance == null){
			instance = new NoInsurance();
		}
		return instance;
	}
	/**
	 * @see HealthInsurance#getFinalCost(Patient)
	 */
	@Override
	public double getFinalCost(Patient patient){
		return patient.getCost()*(1-discount);
	}
}
