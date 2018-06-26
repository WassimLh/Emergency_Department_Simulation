package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartRegistration;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartRegistrationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartRegistration() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartRegistration startRegister = new StartRegistration(sim);
		assertEquals("Wrong simulation", sim, startRegister.getSim());	}

	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
