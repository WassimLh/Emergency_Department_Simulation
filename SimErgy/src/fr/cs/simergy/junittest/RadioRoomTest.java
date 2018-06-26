package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.RadioRoom;

public class RadioRoomTest {

	@Test
	public void testSetOccupied() {
		Patient patient = new Patient("Mary", "Kleener", 2, 4);
		RadioRoom radioRoom = new RadioRoom();
		radioRoom.setOccupied(patient);
		assertTrue(radioRoom.isOccupied());	}

}
