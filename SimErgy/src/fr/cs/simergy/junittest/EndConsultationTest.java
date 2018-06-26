package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndConsultation;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.simulation.EmergencySimulation;

public class EndConsultationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Pablo", "Vilanova", 0, 1);
		Physician physician = new Physician("Samir","Boukamel");
		
		EndConsultation endConsult = new EndConsultation(sim, patient, physician);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endConsult.getSeverityLevel() == patient.getSeverityLevel());
	}

	@Test
	public void testEndConsultation() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Wassim", "Lhourti", 4, 1);
		Physician physician = new Physician("Samir","Boukamel");
		
		EndConsultation endConsult = new EndConsultation(sim,patient,physician);
		
		assertEquals("Wrong simulation",sim,endConsult.getSim());
		assertEquals("Wrong patient",patient,endConsult.getPatient());
		assertEquals("Wrong physician",physician,endConsult.getPhysician());
	}
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
