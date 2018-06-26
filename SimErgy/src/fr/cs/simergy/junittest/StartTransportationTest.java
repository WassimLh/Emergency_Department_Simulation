package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.StartTransportation;
import fr.cs.simergy.simulation.EmergencySimulation;

public class StartTransportationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartTransportation() {
		EmergencySimulation sim = new EmergencySimulation();
		
		StartTransportation startTransportation = new StartTransportation(sim);
		assertEquals("Wrong simulation", sim, startTransportation.getSim());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
