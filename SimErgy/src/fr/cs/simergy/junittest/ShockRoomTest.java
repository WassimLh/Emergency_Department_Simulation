package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.ShockRoom;

public class ShockRoomTest {

	@Test
	public void testSetOccupied() {
		Patient patient = new Patient("Mary", "Kleener", 2, 4);
		ShockRoom shockRoom = new ShockRoom();
		shockRoom.setOccupied(patient);
		assertTrue(shockRoom.isOccupied());	
	}

}
