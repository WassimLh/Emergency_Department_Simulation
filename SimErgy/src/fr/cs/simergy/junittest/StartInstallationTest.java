package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartShockInstallation;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartInstallationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartInstallation() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartShockInstallation startInstall = new StartShockInstallation(sim);
		assertEquals("Wrong simulation", sim, startInstall.getSim());
	}

	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}


}
