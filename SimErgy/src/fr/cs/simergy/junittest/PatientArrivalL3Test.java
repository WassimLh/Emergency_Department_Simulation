package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.events.PatientArrivalL3;
import fr.cs.simergy.simulation.EmergencySimulation;

public class PatientArrivalL3Test {
	@Test
	public void testActions(){
		EmergencySimulation sim1  = new EmergencySimulation(1000.0, 2, 3, 2, 2, 2, 1, 1, 1);
		PatientArrivalL3 patArrivalL3 = new PatientArrivalL3(sim1);
		
		patArrivalL3.actions();
		
		assertTrue(patArrivalL3.getPatient() == sim1.getPatientsWaitingForRegistration().first());	
	}
}
