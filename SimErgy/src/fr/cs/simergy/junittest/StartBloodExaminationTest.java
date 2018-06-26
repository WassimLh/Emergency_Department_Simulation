package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartBloodExamination;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartBloodExaminationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testStartBloodExamination() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartBloodExamination startBloodExam = new StartBloodExamination(sim);
		assertEquals("Wrong simulation", sim, startBloodExam.getSim());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
