package fr.cs.simergy.clientinterface;

import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * When you run this class, the program's simulation is displayed
 * with a given example of the ED's resources.
 *
 */

public class MainSimulator {
	public static void main(String args[]) {
		EmergencySimulation emSim = new EmergencySimulation(10000, 2, 10, 10, 10, 10, 10, 10, 10);
		emSim.setStochasticArrivals();
		emSim.runSimulation();
	}
}
