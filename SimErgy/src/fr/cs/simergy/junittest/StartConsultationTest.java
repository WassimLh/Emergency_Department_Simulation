package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartConsultation;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartConsultationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartConsultation() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartConsultation startConsultation = new StartConsultation(sim);
		assertEquals("Wrong simulation", sim, startConsultation.getSim());
	}

	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}
	

}
