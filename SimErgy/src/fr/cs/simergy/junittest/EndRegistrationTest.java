package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndRegistration;
import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.EmergencySimulation;

public class EndRegistrationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Joséphine", "Abril", 5, 3);
		Nurse nurse = new Nurse("Clémence","Dubois");
		
		EndRegistration endRegister = new EndRegistration(sim,patient,nurse);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endRegister.getSeverityLevel() == patient.getSeverityLevel());
	}

	@Test
	public void testEndRegistration() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Cheikh", "Yazid", 0, 1);
		Nurse nurse = new Nurse("Aroui","Oumayma");
		
		EndRegistration endRegister = new EndRegistration(sim,patient,nurse);
		assertEquals("Wrong simulation",sim,endRegister.getSim());
		assertEquals("Wrong patient",patient,endRegister.getPatient());
		assertEquals("Wrong nurse",nurse,endRegister.getNurse());
	}

	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
