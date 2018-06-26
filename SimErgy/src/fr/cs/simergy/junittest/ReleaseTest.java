package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.Release;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;

public class ReleaseTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Pablo", "Vilanova", 0, 1);
	
		Release release = new Release(sim, patient);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",release.getSeverityLevel() == patient.getSeverityLevel());
	}

	@Test
	public void testRelease() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Pablo", "Vilanova", 0, 1);
	
		Release release = new Release(sim, patient);
		assertEquals("Wrong simulation",sim,release.getSim());
		assertEquals("Wrong patient",patient,release.getPatient());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
