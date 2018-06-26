package fr.cs.simergy.clui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import fr.cs.simergy.events.PatientArrival;
import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * Defines how files are read in our CLUI
 *
 */
public class SimErgyFileReader {
	public static void executeTextFileLineByLine(EmergencySimulation sim, String fileName) throws FileReaderException { 
		//String returnValue = ""; 
		FileReader file = null; 
		BufferedReader reader = null; 
		
		try { 
			file = new FileReader(fileName); // a FileReader for reading bytebybyte 
			reader = new BufferedReader(file); // wrapping a FileReader into a BufferedReader for reading lineby line 
			String line = ""; 
			while ((line = reader.readLine()) != null) { // read the file linebyline 
				//returnValue += line + '\n';

				try {
					String[] option = line.split(" ", 7);
					switch (option[0]) {
					case "name":
						sim.setName(option[1]);
						break;
					case "period":
						sim.setSimPeriod(Double.parseDouble(option[1]));
						// System.out.println("Je suis là");
						break;
					case "nbPhysicians":
						sim.setNbPhysicians(Integer.parseInt(option[1]));
						// System.out.println("Je suis là2");
						break;
					case "nbNurses":
						sim.setNbNurses(Integer.parseInt(option[1]));
						// System.out.println("Je suis là3");
						break;
					case "nbTransporters":
						sim.setNbTransporters(Integer.parseInt(option[1]));
						// System.out.println("Je suis là4");
						break;
					case "nbBoxRoom":
						sim.setNbBoxRoom(Integer.parseInt(option[1]));
						// System.out.println("Je suis là5");
						break;
					case "nbShockRoom":
						sim.setNbShockRoom(Integer.parseInt(option[1]));
						// System.out.println("Je suis là6");
						break;
					case "nbBloodRoom":
						sim.setNbBloodRoom(Integer.parseInt(option[1]));
						// System.out.println("Je suis là7");
						break;
					case "nbMriRoom":
						sim.setNbMRIRoom(Integer.parseInt(option[1]));
						// System.out.println("Je suis là8");
						break;
					case "nbRadioRoom":
						sim.setNbRadioRoom(Integer.parseInt(option[1]));
						// System.out.println("Je suis là9");
						break;
					case "nbL1Patient":
						for (int i=0; i<Integer.parseInt(option[1]); i++){
							new PatientArrival(sim, 1, 0).schedule(0);
						}
						break;
					case "nbL2Patient":
						for (int i=0; i<Integer.parseInt(option[1]); i++){
							new PatientArrival(sim, 2, 0).schedule(0);
						}
						break;
					case "nbL3Patient":
						for (int i=0; i<Integer.parseInt(option[1]); i++){
							new PatientArrival(sim, 3, 0).schedule(0);
						}
						break;
					case "nbL4Patient":
						for (int i=0; i<Integer.parseInt(option[1]); i++){
							new PatientArrival(sim, 4, 0).schedule(0);
						}
						break;
					case "nbL5Patient":
						for (int i=0; i<Integer.parseInt(option[1]); i++){
							new PatientArrival(sim, 5, 0).schedule(0);
						}
						break;
					case "runSimulation":
						sim.setStochasticArrivals();
// 						sim = new EmergencySimulation(sim.getSimPeriod(), sim.getNbPhysicians(), sim.getNbNurses(), 
//								sim.getNbTransporters(), sim.getNbBoxRoom(), sim.getNbShockRoom(), 
//								sim.getNbBloodRoom(), sim.getNbMRIRoom(), sim.getNbRadioRoom());
						sim.runSimulation();
						break;
					default:
						throw new FileReaderException("The name of one of your initializations is invalid --- Please check specifications");
					}
				} catch (Exception e) {
					System.out.println("Your text file syntax is incorrect --- Please check specifications");
					break;
				}	
				
				
				
				
			} 
		} catch (FileNotFoundException e) { 
			throw new FileReaderException("We cannot found the file you requested!"); 
		} catch (IOException e) { 
			throw new FileReaderException("Tour text syntax is inccoorect!"); 
		} finally { 
			if (reader != null) { 
				try {reader.close();
			} catch (IOException e) {} 
			} if (file != null) { 
				try {file.close();
			} catch (IOException e) {} 
			}
		}
		// System.out.println(sim.getNbMRIRoom());
	}
}
