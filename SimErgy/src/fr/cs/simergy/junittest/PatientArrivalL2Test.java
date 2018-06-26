package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.events.PatientArrivalL2;
import fr.cs.simergy.simulation.EmergencySimulation;

public class PatientArrivalL2Test {
	@Test
	public void testActions(){
		EmergencySimulation sim1  = new EmergencySimulation(1000.0, 2, 3, 2, 2, 2, 1, 1, 1);
		PatientArrivalL2 patArrivalL2 = new PatientArrivalL2(sim1);
		
		patArrivalL2.actions();
		
		assertTrue(patArrivalL2.getPatient() == sim1.getPatientsWaitingForRegistration().first());	
	}

}
