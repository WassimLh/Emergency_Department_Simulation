package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.BoxRoom;

public class BoxRoomTest {

	@Test
	public void testSetOccupied() {
		Patient patient = new Patient("Mary", "Kleener", 2, 4);
		BoxRoom boxRoom = new BoxRoom();
		boxRoom.setOccupied(patient);
		assertTrue(boxRoom.isOccupied());	
	}

}
