package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.BloodTestRoom;
import fr.cs.simergy.simulation.EmergencySimulation;

public class PatientTest {
	EmergencySimulation sim = new EmergencySimulation();
	Patient patient = new Patient("Philippe","Crillon",1,3);

	@Test
	public void testChangeLocation() {
		BloodTestRoom bloodRoom = new BloodTestRoom();
		patient.changeLocation(bloodRoom);
		
		assertEquals("Wrong room",patient.getLocation(),bloodRoom);
	}



}
