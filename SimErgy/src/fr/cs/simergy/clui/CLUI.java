package fr.cs.simergy.clui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * Generates loops to print commands and results
 * @author Abdou
 *
 */
public class CLUI implements Runnable {

	private EmergencySimulation sim;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CommandExecutor command = new CommandExecutor();
	boolean yetCreated = false;
	/**
	 * CLUI Constructor with a given simulation
	 * @param sim
	 */
	public CLUI(EmergencySimulation sim) {
		this.sim = sim;
	}
	/**
	 * Allows to start the CLUI simulation by creating ED specifications
	 * or importing them.
	 * @param input
	 * @throws CommandException
	 * @throws IOException
	 * @throws FileReaderException
	 */
	public void createSimulation(String[] input) throws CommandException, IOException, FileReaderException {
		switch (input[0]) {
		case "createED":
			command.createED(sim, input);
			yetCreated = true;
			System.out.print("ED created \n");
			break;
		case "importInitED":
			command.importEDfile(sim, input);
			yetCreated = true;
			System.out.print("ED imported \n");
			break;
		case "runTest":
			command.importEDfile(sim, input);
			sim = new EmergencySimulation();
			break;
		default:
			throw new CommandException();
		}
	}
	/**
	 * Runs the CLUI with all of its possibilities
	 */
	@Override
	public void run() {
		System.out.println("WELCOME TO SIMERGY! \n");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("LIST OF COMMANDS FOR CLIENT-COMMAND USER INTERFACE");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("These are list of commands allowed by the Command-Line user interface : \n"
				+ "createED <EDname> \n" + "addNurse <NurseName, NurseSurname> \n" + "addNurse <> \n"
				+ "addPhysi <PhysiName, PhysiSurname> \n" + "addPhysi <> \n" + "addTransporter <> \n"
				+ "addRoom <RoomType, RoomName> \n" + "addRadioService <DistType, DistParams> \n"
				+ "addMRI <DistType, DistParams> \n" + "addBloodTest <DistType, DistParams> \n"
				+ "setL1arrivalDist <DistType, DistParams> \n" + "setL2arrivalDist <DistType, DistParams> \n"
				+ "setL3arrivalDist <DistType, DistParams> \n" + "setL4arrivalDist <DistType, DistParams> \n"
				+ "setL5arrivalDist <DistType, DistParams> \n"
				+ "addPatient <PatientName, PatientSurname, SeverityLevel, HealthInsurance> \n"
				+ "addPatient <SeverityLevel> \n" + "registerPatient <PatientID> \n"
				+ "setPatientInsurance <PatientID, HealthInsurance> \n" + "executeEvent <> \n" + "display <> \n"
				+ "restart <> \n" + "quit <> \n");
		System.out.println(
				"---------------------------------------------------------------------------------------------- \n \n");

		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("SOME PRECISIONS ABOUT PARAMETERS");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("DistType is either \n" + "\t 'exponential' with a single positive real value parameter \n"
				+ "\t 'deterministic' with a single positive real value parameter \n"
				+ "\t 'uniform' with a pair (a,b) of real value parameters such as 0<a<b ");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("RoomType is either 'boxRoom', or 'shockRoom'");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("HealthInsurance is either 'noInsurance', 'silverInsurance', or 'goldInsurance'");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("\n");

		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.println("SIMERGY COMMAND-LINE USER INTERFACE");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.print("Please select an option: \n ");
		System.out.print(
				"\t Create a new ED: createED <EDname> \n " + "\t Import an inital ED: importInitED <Filename> \n"
					+	"\t Run a test case scenario: runTest <Filename> \n");
		System.out.println("----------------------------------------------------------------------------------------------");
		while (!yetCreated) {
			System.out.print("Please enter your command : ");
			try {
				String[] input = br.readLine().split(" ", 2);
				this.createSimulation(input);
			} catch (CommandException e) {
				System.out.print("Invalid command, please use either createED,importInitED, or runTest \n"
						+ "---------------------------------------------------------------------------------------------- \n");
			} catch (IOException e) {
				System.out.println("Please review the syntax of your file");
				System.out.println("----------------------------------------------------------------------------------------------");
			} catch (FileReaderException e) {
				System.out.println(e.getMessage());
				System.out.println("----------------------------------------------------------------------------------------------");
			}
		}

		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("You could apply one of the commands defined above for this specific EmergencyDepartment");
		System.out.println("If you want to work on another Emergency Department please type the command 'restart'");
		boolean commandLoop = true;
		while (commandLoop) {
			System.out.print(
					"-----------------------------------------------------------------------------------------------\n");
			System.out.print("Please enter your command : ");
			try {
				String[] option = br.readLine().split(" ", 5);
				switch (option[0]) {
				case "restart":
					this.sim = new EmergencySimulation();
					yetCreated = false;
					run();
				case "exit":
					commandLoop = false;
					System.out.println("Thank you for using SimErgy software. See you !");
					break;
				case "addRoom":
					command.addRoom(sim, option);
					break;
				case "addRadioService":
					command.addRadioService(sim, option);
					break;
				case "addMRI":
					command.addMRI(sim, option);
					break;
				case "addBloodTest":
					command.addBloodTest(sim, option);
					break;
				case "addNurse":
					command.addNurse(sim, option);
					break;
				case "addPhysi":
					command.addPhysi(sim, option);
					break;
				case "addTransporter":
					command.addTransporter(sim, option);
					break;
				case "setL1arrivalDist":
					command.setL1arrivalDist(sim, option);
					break;
				case "setL2arrivalDist":
					command.setL2arrivalDist(sim, option);
					break;
				case "setL3arrivalDist":
					command.setL3arrivalDist(sim, option);
					break;
				case "setL4arrivalDist":
					command.setL4arrivalDist(sim, option);
					break;
				case "setL5arrivalDist":
					command.setL5arrivalDist(sim, option);
					break;
				case "addPatient":
					command.addPatient(sim, option);
					break;
				case "registerPatient":
					command.registerPatient(sim, option);
					break;
				case "setPatientInsurance":
					command.setPatientInsurance(sim, option);
					break;
				case "executeEvent":
					command.executeEvent(sim);
					break;
				case "display":
					command.displaySim(sim);
					break;
				// case "save":
				// command.saveRegisters(sim);
				// break;
				case "runSimulation":
					command.runSimulation(sim);
					System.out.println(
							"-----------------------------------------------------------------------------------------------");
					System.out.println("This simulation has been ran for a time period of : " + sim.getSimPeriod());
					break;
				default:
					throw new CommandException(
							"The name of your method is invalid --- Please check specifications above");
				}
			} catch (CommandException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.print("Seems like there's some problem with the input you wrote, please try again \n");
			}
		}

	}

}
