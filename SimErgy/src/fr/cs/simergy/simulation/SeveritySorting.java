package fr.cs.simergy.simulation;

import fr.cs.simergy.humanresources.Patient;
/**
 * Defines a specific strategy to sort patients, 
 * as the most severe ones are the first to get served 
 *
 */
public class SeveritySorting implements SortingPatients {
	/**
	 * @see SortingPatients#getPriorityPatient(Head)
	 */
	@Override
	public Patient getPriorityPatient(Head s){
		Patient p = (Patient) s.first();
		p.out();
		p.into(s);
		for(int i = 0; i < s.cardinal(); i++){
			if (((Patient) s.first()).getSeverityLevel() < p.getSeverityLevel()){
				p = (Patient) s.first();
			}
			p.out();
			p.into(s);
		}
		return p;
	}
}
