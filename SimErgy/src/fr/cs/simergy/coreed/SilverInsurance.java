package fr.cs.simergy.coreed;

import fr.cs.simergy.humanresources.Patient;
/**
 * Defines a health insurance for which 
 * the patient has 50% of discount
 *
 */
public class SilverInsurance implements HealthInsurance {
	private static SilverInsurance instance = null;
	private double discount = 0.5;
	
	private SilverInsurance(){};
	
	/**
	 * Method to implement the Singleton pattern for health insurance
	 */
	public static SilverInsurance getInstance(){
		if (instance == null){
			instance = new SilverInsurance();
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
