package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.simulation.SeveritySorting;
import fr.cs.simergy.simulation.Head;

public class SeveritySortingTest {
	
	@Test
	public void testGetPriorityPatient(){
		SeveritySorting severitySort = new SeveritySorting();
		//Creating 5 patients with different severity level
		Patient patientL1 = new Patient(1,0);
		Patient patientL2 = new Patient(2,0);
		Patient patientL3 = new Patient(3,0);
		Patient patientL4 = new Patient(4,0);
		Patient patientL5 = new Patient(5,0);
		//Creating a head object and adding those patients to it
		Head s = new Head();
		patientL1.into(s);
		patientL2.into(s);
		patientL3.into(s);
		patientL4.into(s);
		patientL5.into(s);
		//Asserting that the patients are sorted in the correct severity order
		assertTrue(severitySort.getPriorityPatient(s) == patientL1);
		patientL1.out();
		assertTrue(severitySort.getPriorityPatient(s) == patientL2);
		patientL2.out();
		assertTrue(severitySort.getPriorityPatient(s) == patientL3);
		patientL3.out();
		assertTrue(severitySort.getPriorityPatient(s) == patientL4);
		patientL4.out();
		assertTrue(severitySort.getPriorityPatient(s) == patientL5);
	}
}
