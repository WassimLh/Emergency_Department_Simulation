package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.events.PatientArrivalL5;
import fr.cs.simergy.simulation.EmergencySimulation;

public class PatientArrivalL5Test {
	@Test
	public void testActions(){
		EmergencySimulation sim1  = new EmergencySimulation(1000.0, 2, 3, 2, 2, 2, 1, 1, 1);
		PatientArrivalL5 patArrivalL5 = new PatientArrivalL5(sim1);
		
		patArrivalL5.actions();
		
		assertTrue(patArrivalL5.getPatient() == sim1.getPatientsWaitingForRegistration().first());	
	}

}
