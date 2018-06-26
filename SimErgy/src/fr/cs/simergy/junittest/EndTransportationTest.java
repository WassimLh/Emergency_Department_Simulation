package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndTransportation;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Transporter;
import fr.cs.simergy.simulation.EmergencySimulation;

public class EndTransportationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Lilia", "Curie", 6, 3);
		Transporter transporter = new Transporter("Besson","Luc");
		
		EndTransportation endTransport = new EndTransportation(sim,patient,transporter);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endTransport.getSeverityLevel() == patient.getSeverityLevel());
	}

	@Test
	public void testEndTransportation() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Lilia", "Curie", 6, 3);
		Transporter transporter = new Transporter("Besson","Luc");
		
		EndTransportation endTransport = new EndTransportation(sim,patient,transporter);
		assertEquals("Wrong simulation",sim,endTransport.getSim());
		assertEquals("Wrong patient",patient,endTransport.getPatient());
		assertEquals("Wrong transporter",transporter,endTransport.getTransporter());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
