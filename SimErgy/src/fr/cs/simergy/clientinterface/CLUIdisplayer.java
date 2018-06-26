package fr.cs.simergy.clientinterface;

import fr.cs.simergy.clui.CLUI;
import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * When you run this class, the CLUI is displayed
 *
 */
public class CLUIdisplayer {
	public static void main(String[] args){
		CLUI clui = new CLUI(new EmergencySimulation());
		Thread thread = new Thread(clui);
		thread.start();
	}
}
