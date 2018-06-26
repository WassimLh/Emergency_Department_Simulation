package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndInstallation;
import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.BoxRoom;
import fr.cs.simergy.materialresources.ShockRoom;
import fr.cs.simergy.simulation.EmergencySimulation;

public class EndInstallationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Pablo", "Vilanova", 0, 4);
		Nurse nurse = new Nurse("El Aamrani","Ahmed");
		BoxRoom boxRoom = new BoxRoom();
		
		EndInstallation endInstall = new EndInstallation(sim,patient,nurse,boxRoom);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endInstall.getSeverityLevel() == patient.getSeverityLevel());
	}

	
	@Test
	public void testEndInstallation() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Pablo", "Vilanova", 0, 1);
		Nurse nurse = new Nurse("El Aamrani","Ahmed");
		ShockRoom shockRoom = new ShockRoom();
		
		EndInstallation endInstall = new EndInstallation(sim,patient,nurse,shockRoom);
		assertEquals("Wrong simulation",sim,endInstall.getSim());
		assertEquals("Wrong patient",patient,endInstall.getPatient());
		assertEquals("Wrong nurse",nurse,endInstall.getNurse());
		assertEquals("Wrong Room",shockRoom,endInstall.getRoom());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
