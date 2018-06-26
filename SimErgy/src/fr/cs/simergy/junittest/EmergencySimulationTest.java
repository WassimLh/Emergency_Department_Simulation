package fr.cs.simergy.junittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.simulation.EmergencySimulation;

public class EmergencySimulationTest {
	EmergencySimulation sim1 = new EmergencySimulation(1000.0, 2, 3, 2, 2, 2, 1, 1, 1);
	
	@Test
	public void testEmergencySimulation(){
		//Once the EmergencySimulation is created, we assert that the resources 
		//queues have been correctly initialized
		assertEquals(sim1.getIdlePhysicians().cardinal(), 2);
		assertEquals(sim1.getIdleNurses().cardinal(), 3);
		assertEquals(sim1.getIdleTransporters().cardinal(), 2);
		assertEquals(sim1.getEmptyBoxRooms().cardinal(), 2);
		assertEquals(sim1.getEmptyShockRooms().cardinal(), 2);
		assertEquals(sim1.getEmptyBloodRooms().cardinal(), 1);
		assertEquals(sim1.getEmptyMRIRooms().cardinal(), 1);
		assertEquals(sim1.getEmptyRadioRooms().cardinal(), 1);
	}
	
	@Test
	public void testSetStochasticArrivals(){
		sim1.setStochasticArrivals();
		sim1.runSimulation();
		//If the LOS KPI's have been computed for each severity level,
		//this means that patient arrivals have been correctly initialized with the setStochasticArrivals() method.
		//Otherwise, we would have 0 as a result for those KPI's.
		assertTrue(sim1.computeAvgLOS_L1() > 0);
		assertTrue(sim1.computeAvgLOS_L2() > 0);
		assertTrue(sim1.computeAvgLOS_L3() > 0);
		assertTrue(sim1.computeAvgLOS_L4() > 0);
		assertTrue(sim1.computeAvgLOS_L5() > 0);
	}

	
}
