package fr.cs.simergy.junittest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.materialresources.MRIRoom;

public class MRIRoomTest {

	@Test
	public void testSetOccupied() {
		Patient patient = new Patient("Mary", "Kleener", 2, 4);
		MRIRoom mriRoom = new MRIRoom();
		mriRoom.setOccupied(patient);
		assertTrue(mriRoom.isOccupied());
	}


}
