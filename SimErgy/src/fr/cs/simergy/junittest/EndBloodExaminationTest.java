package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndBloodExamination;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.BloodTestRoom;
import fr.cs.simergy.simulation.EmergencySimulation;


public class EndBloodExaminationTest {
	
	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Pablo", "Vilanova", 0, 4);
		BloodTestRoom bloodRoom = new BloodTestRoom();
		
		EndBloodExamination endBloodExam = new EndBloodExamination(sim, patient, bloodRoom);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endBloodExam.getSeverityLevel() == patient.getSeverityLevel());
		
	}
	
	@Test
	public void testEndBloodExamination() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Jean", "Drian", 2, 1);
		BloodTestRoom bloodRoom = new BloodTestRoom();
		
		EndBloodExamination endBloodExam = new EndBloodExamination(sim,patient,bloodRoom);
		
		assertEquals("Wrong simulation",sim,endBloodExam.getSim());
		assertEquals("Wrong patient",patient,endBloodExam.getPatient());
		assertEquals("Wrong blood Room",bloodRoom,endBloodExam.getRoom());
	}
	
	@Ignore
	@Test
	public void testReport() {
	}
	
	

}
