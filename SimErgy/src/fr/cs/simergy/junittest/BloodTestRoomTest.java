package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.BloodTestRoom;

public class BloodTestRoomTest {

	@Test
	public void testSetOccupied() {
		Patient patient = new Patient("Mary", "Kleener", 2, 4);
		BloodTestRoom bloodTestRoom = new BloodTestRoom();
		bloodTestRoom.setOccupied(patient);
		assertTrue(bloodTestRoom.isOccupied());
	}

}
