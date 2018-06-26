package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import fr.cs.simergy.events.EndMRIExamination;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.MRIRoom;
import fr.cs.simergy.simulation.EmergencySimulation;

public class EndMRIExaminationTest {

	@Test
	public void testActions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSeverityLevel() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Jean", "Moulin", 9, 5);
		MRIRoom mriRoom = new MRIRoom();
		
		EndMRIExamination endMRIExam = new EndMRIExamination(sim,patient,mriRoom);
		assertTrue("The getSeverityLevel() method does not return the correct serverity level",endMRIExam.getSeverityLevel() == patient.getSeverityLevel());

	}

	@Test
	public void testEndMRIExamination() {
		EmergencySimulation sim = new EmergencySimulation();
		Patient patient = new Patient("Jean", "Drian", 3, 2);
		MRIRoom mriRoom = new MRIRoom();
		
		EndMRIExamination endMRIExam = new EndMRIExamination(sim,patient,mriRoom);
		
		assertEquals("Wrong simulation",sim,endMRIExam.getSim());
		assertEquals("Wrong patient",patient,endMRIExam.getPatient());
		assertEquals("Wrong MRI Room",mriRoom,endMRIExam.getRoom());
	}
	
	@Ignore
	@Test
	public void testReport() {
		fail("Not yet implemented");
	}

}
