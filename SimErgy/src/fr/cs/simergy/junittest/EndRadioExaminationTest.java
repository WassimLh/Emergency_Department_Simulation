package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndRadioExamination;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.RadioRoom;
import fr.cs.simergy.simulation.EmergencySimulation;

public class EndRadioExaminationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Jean", "Drian", 2, 1);
		RadioRoom radioRoom = new RadioRoom();
		
		EndRadioExamination endRadioExam = new EndRadioExamination(sim,patient,radioRoom);
		
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endRadioExam.getSeverityLevel() == patient.getSeverityLevel());
	}

	@Test
	public void testEndRadioExamination() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Jean", "Drian", 2, 1);
		RadioRoom radioRoom = new RadioRoom();
		
		EndRadioExamination endRadioExam = new EndRadioExamination(sim,patient,radioRoom);
		
		assertEquals("Wrong simulation",sim,endRadioExam.getSim());
		assertEquals("Wrong patient",patient,endRadioExam.getPatient());
		assertEquals("Wrong Radio Room",radioRoom,endRadioExam.getRoom());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
