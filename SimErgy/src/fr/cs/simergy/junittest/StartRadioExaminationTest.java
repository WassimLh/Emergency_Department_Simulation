package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartRadioExamination;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartRadioExaminationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartRadioExamination() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartRadioExamination startRadioExam = new StartRadioExamination(sim);
		assertEquals("Wrong simulation", sim, startRadioExam.getSim());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
