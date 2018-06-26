package fr.cs.simergy.clui;

import java.io.IOException;

import fr.cs.simergy.coreed.DeterministicDist;
import fr.cs.simergy.coreed.ExponentialDist;
import fr.cs.simergy.coreed.GoldInsurance;
import fr.cs.simergy.coreed.HealthInsurance;
import fr.cs.simergy.coreed.NoInsurance;
import fr.cs.simergy.coreed.SilverInsurance;
import fr.cs.simergy.coreed.UniformDist;
import fr.cs.simergy.events.PatientArrival;
import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.humanresources.Transporter;
import fr.cs.simergy.materialresources.BloodTestRoom;
import fr.cs.simergy.materialresources.BoxRoom;
import fr.cs.simergy.materialresources.MRIRoom;
import fr.cs.simergy.materialresources.RadioRoom;
import fr.cs.simergy.materialresources.ShockRoom;
import fr.cs.simergy.simulation.EmergencySimulation;
import fr.cs.simergy.simulation.Event;
/**
 * Helps to lighten the CLUI class, as we use a CommandExecutor
 * at each case that we meet
 *
 */
public class CommandExecutor {

	public CommandExecutor() {
	}

	public void createED(EmergencySimulation sim, String[] input) {
		if (input.length == 2) {
			sim.setName(input[1]);		
		}
	}

	public void importEDfile(EmergencySimulation sim, String[] input) throws IOException, FileReaderException {
		if (input.length == 1) {
			// sim.addFromFile("eval/my_EmergencySimulation.ini");
			SimErgyFileReader.executeTextFileLineByLine(sim, "my_simergy.ini");
		} else {
			SimErgyFileReader.executeTextFileLineByLine(sim, input[1]);
			// sim.addFromFile(input[1]);
		}
	}
	
	public void addRoom(EmergencySimulation sim, String[] option) throws CommandException {
		String type = option[1];
		String roomName;
		switch (type) {
		case "shockRoom":
			if (option.length == 3) {
				roomName = option[2];
			} else {
				roomName = "defaultShockRoom";
			}
			(new ShockRoom(roomName)).into(sim.getEmptyShockRooms());
			System.out.println("Shock Room added");
			break;
		case "boxRoom":
			if (option.length == 3) {
				roomName = option[2];
			} else {
				roomName = "defaultBoxRoom";
			}
			(new BoxRoom(roomName)).into(sim.getEmptyBoxRooms());
			System.out.println("Box Room added");
			break;
		default:
			throw new CommandException("Invalid syntax --- Please for RoomType choose either 'boxRoom' or 'shockRoom'");
		}
	}

	public void addRadioService(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setRadioDist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setRadioDist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setRadioDist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		new RadioRoom().into(sim.getEmptyRadioRooms());
		System.out.println("Radio room added");
	}

	public void addMRI(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setMriDist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setMriDist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setMriDist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		
		new MRIRoom().into(sim.getEmptyMRIRooms());
		System.out.println("MRI room added");
	}

	public void addBloodTest(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setBloodDist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setBloodDist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setBloodDist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		
		new BloodTestRoom().into(sim.getEmptyBloodRooms());
		System.out.println("BloodTest room added");
	}

	public void addNurse(EmergencySimulation sim, String[] option) {
		Nurse nurse;
		if (option.length == 3)
			nurse = new Nurse(option[1], option[2]);
		else
			nurse = new Nurse();
		nurse.into(sim.getIdleNurses());
		System.out.println("Nurse " + nurse.getName() + " added");
	}

	public void addPhysi(EmergencySimulation sim, String[] option) {
		Physician physician;
		if (option.length == 3)
			physician = new Physician(option[1], option[2]);
		else
			physician = new Physician();
		physician.into(sim.getIdlePhysicians());
		System.out.println("Physician " + physician.getName() + " added");
	}

	public void addTransporter(EmergencySimulation sim, String[] option) {
		Transporter transporter;
		if (option.length == 3)
			transporter = new Transporter(option[1], option[2]);
		else
			transporter = new Transporter();
		transporter.into(sim.getIdleTransporters());
		System.out.println("Transporter " + transporter.getName() + " added");
	}

	public void setL1arrivalDist(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setL1Dist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setL1Dist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setL1Dist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		System.out.println("L1 distribution is set");
		
	}

	public void setL2arrivalDist(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setL2Dist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setL2Dist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setL2Dist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		System.out.println("L2 distribution is set");
	}

	public void setL3arrivalDist(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setL3Dist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setL3Dist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setL3Dist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		System.out.println("L3 distribution is set");
	}

	public void setL4arrivalDist(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setL4Dist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setL4Dist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setL4Dist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		System.out.println("L4 distribution is set");
	}

	public void setL5arrivalDist(EmergencySimulation sim, String[] option) throws CommandException {
		String distType = option[1];
		double param1;
		try{
			param1 = Double.parseDouble(option[2]);
		} catch (NumberFormatException nfe){
			throw new CommandException("Your distribution parameters should be real numbers");
		}
		
		if (param1<=0)
			throw new CommandException("Your distribution parameters should be strictly positive");
		
		switch(distType){
		case "exponential":
			sim.setL5Dist(new ExponentialDist(param1));
			break;
		case "deterministic":
			sim.setL5Dist(new DeterministicDist(param1));
			break;
		case "uniform":
			if (option.length >= 4){
				double param2;
				try{
					param2 = Double.parseDouble(option[3]);
				} catch (NumberFormatException nfe){
					throw new CommandException("Your distribution parameters should be real numbers");
				}
				if (param1>=param2)
					throw new CommandException("For uniform distribution, your first parameter should be lower than the second");
				sim.setL5Dist(new UniformDist(param1, param2));
				break;
			}
			else{
				throw new CommandException("Uniform parameters should be a pair of real values");
			}
		default:
			throw new CommandException("Invalid syntax --- Please for DistType choose either 'exponential' or 'deterministic' or 'uniform'");
		}
		
		System.out.println("L5 distribution is set");
	}

	public void addPatient(EmergencySimulation sim, String[] option) throws CommandException {
		Patient patient;
		if (option.length == 5){
			String name = option[1];
			String surname = option[2];
			int sevLevel;
			try{
				sevLevel = Integer.parseInt(option[3]);
			} catch(NumberFormatException nfe){
				throw new CommandException("Severity level of the patient should be integer");
			}
			
			if (sevLevel!=1 && sevLevel!=2 && sevLevel!=3 && sevLevel!=4 && sevLevel!=5){
				throw new CommandException("Severity level should be an integer from {1, 2, 3, 4, 5}");
			}
			
			HealthInsurance hi = NoInsurance.getInstance();
			switch (option[4]) {
			case "noInsurance":
				hi = NoInsurance.getInstance();
				break;
			case "silverInsurance":
				hi = SilverInsurance.getInstance();
				break;
			case "goldInsurance":
				hi = GoldInsurance.getInstance();
				break;
			default:
				throw new CommandException("Invalid syntax --- Please for HealthInsurance choose either 'noInsurance' "
						+ "or 'silverInsurance' or 'goldInsurance'" );
			}
			patient = new Patient(name, surname, sevLevel, sim.getTime(), hi);
			sim.getPatientsInSimulation().add(patient);
			new PatientArrival(sim, patient).schedule(sim.getTime());
		}
		
		else if (option.length == 2){
			int sevLevel;
			try{
				sevLevel = Integer.parseInt(option[1]);
			} catch(NumberFormatException nfe){
				throw new CommandException("Severity level of the patient should be integer");
			}
			
			if (sevLevel!=1 && sevLevel!=2 && sevLevel!=3 && sevLevel!=4 && sevLevel!=5)
				throw new CommandException("Severity level should be an integer from {1, 2, 3, 4, 5}");
				
			patient = new Patient(sevLevel, sim.getTime());
			sim.getPatientsInSimulation().add(patient);
			new PatientArrival(sim, patient).schedule(sim.getTime());
		}
		else{
			throw new CommandException("Your parameters for addPatient are incorrect --- Please check above in the definition");
		}
		
		System.out.println("Patient " + patient.getName() + " added");
	}	
	
	
	// Already requested when adding Patient to the ED
	public void registerPatient(EmergencySimulation sim, String[] option) {
		System.out.println("Please wait for your turn, patients that are waiting for registration will all "
				+ "be served as soon as possible");
	}

	public void setPatientInsurance(EmergencySimulation sim, String[] option) throws CommandException {
		int patientID = Integer.parseInt(option[1]);
		HealthInsurance hi = NoInsurance.getInstance();
		switch (option[2]) {
		case "noInsurance":
			hi = NoInsurance.getInstance();
			break;
		case "silverInsurance":
			hi = SilverInsurance.getInstance();
			break;
		case "goldInsurance":
			hi = GoldInsurance.getInstance();
			break;
		default:
			throw new CommandException("Invalid syntax --- Please for HealthInsurance choose either 'noInsurance' "
					+ "or 'silverInsurance' or 'goldInsurance'" );
		}
		
		boolean b = true;
		for (Patient patient : sim.getPatientsInSimulation()) {
			if (patient.getPatientID() == patientID) {
				patient.setInsurance(hi);
				b = false;
			}
		}
		
		if (b){
			throw new CommandException("There is no patient with the same ID");
		}
		
		System.out.println("Health Insurance set");
	}

	public void runSimulation(EmergencySimulation sim){
		sim.runSimulation();
	}
	
	public void executeEvent(EmergencySimulation sim) {
		Event.executeFirstEvent();
		sim.updateEventQueue();
	}
	
	public void displaySim(EmergencySimulation sim) {
		System.out.println(sim);
	}
	

}
