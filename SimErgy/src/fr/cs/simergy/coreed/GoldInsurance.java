package fr.cs.simergy.coreed;

import fr.cs.simergy.humanresources.Patient;
/**
 * Defines a health insurance for which 
 * the patient has 80% of discount
 *
 */
public class GoldInsurance implements HealthInsurance {
	private static GoldInsurance instance = null;
	private double discount = 0.8;
	
	private GoldInsurance(){};
	/**
	 * Method to implement the Singleton pattern for health insurance
	 */
	public static GoldInsurance getInstance(){
		if (instance == null){
			instance = new GoldInsurance();
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
