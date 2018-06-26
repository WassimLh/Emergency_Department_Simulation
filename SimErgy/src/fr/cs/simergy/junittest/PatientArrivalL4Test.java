package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.events.PatientArrivalL4;
import fr.cs.simergy.simulation.EmergencySimulation;

public class PatientArrivalL4Test {
	@Test
	public void testActions(){
		EmergencySimulation sim1  = new EmergencySimulation(1000.0, 2, 3, 2, 2, 2, 1, 1, 1);
		PatientArrivalL4 patArrivalL4 = new PatientArrivalL4(sim1);
		
		patArrivalL4.actions();
		
		assertTrue(patArrivalL4.getPatient() == sim1.getPatientsWaitingForRegistration().first());	
	}
}
