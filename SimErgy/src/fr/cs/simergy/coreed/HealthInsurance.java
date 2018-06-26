package fr.cs.simergy.coreed;

import fr.cs.simergy.humanresources.Patient;

/**
 * A HealthInsurance, if the patient has one, offers to him
 * discounts on any ED service. A silver insurance gives 50% discount
 * and a gold insurance gives 80% discount.
 */
public interface HealthInsurance {
	/**
	 * Computes the final cost of a patient in the ED
	 * depending on his health insurance
	 * @param patient
	 * @return cost of the ED process for a patient
	 */
	public double getFinalCost(Patient patient);

}
