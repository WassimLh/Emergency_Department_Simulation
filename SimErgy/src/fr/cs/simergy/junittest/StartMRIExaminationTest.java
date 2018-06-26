package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartMRIExamination;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartMRIExaminationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartMRIExamination() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartMRIExamination startMRIExam = new StartMRIExamination(sim);
		assertEquals("Wrong simulation", sim, startMRIExam.getSim());	
	}

	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
