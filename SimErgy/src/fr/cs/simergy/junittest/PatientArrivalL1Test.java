package fr.cs.simergy.junittest;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.cs.simergy.events.PatientArrivalL1;
import fr.cs.simergy.simulation.EmergencySimulation;

public class PatientArrivalL1Test {
	
	@Test
	public void testActions(){
		EmergencySimulation sim1  = new EmergencySimulation(1000.0, 2, 3, 2, 2, 2, 1, 1, 1);
		PatientArrivalL1 patArrivalL1 = new PatientArrivalL1(sim1);
		
		patArrivalL1.actions();
		
		assertTrue(patArrivalL1.getPatient() == sim1.getPatientsWaitingForRegistration().first());	
	}

}
