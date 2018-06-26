package fr.cs.simergy.simulation;

import java.text.DecimalFormat;
import java.util.ArrayList;

import fr.cs.simergy.coreed.RandomDist;
import fr.cs.simergy.coreed.UniformDist;
import fr.cs.simergy.events.PatientArrivalL1;
import fr.cs.simergy.events.PatientArrivalL2;
import fr.cs.simergy.events.PatientArrivalL3;
import fr.cs.simergy.events.PatientArrivalL4;
import fr.cs.simergy.events.PatientArrivalL5;
import fr.cs.simergy.events.StartBloodExamination;
import fr.cs.simergy.events.StartBoxInstallation;
import fr.cs.simergy.events.StartConsultation;
import fr.cs.simergy.events.StartMRIExamination;
import fr.cs.simergy.events.StartRadioExamination;
import fr.cs.simergy.events.StartRegistration;
import fr.cs.simergy.events.StartShockInstallation;
import fr.cs.simergy.events.StartTransportation;
import fr.cs.simergy.humanresources.Nurse;
import fr.cs.simergy.humanresources.Patient;
import fr.cs.simergy.humanresources.Physician;
import fr.cs.simergy.humanresources.Transporter;
import fr.cs.simergy.materialresources.BloodTestRoom;
import fr.cs.simergy.materialresources.BoxRoom;
import fr.cs.simergy.materialresources.MRIRoom;
import fr.cs.simergy.materialresources.RadioRoom;
import fr.cs.simergy.materialresources.ShockRoom;
/**
 * This class defines how a simulation of the ED behaves 
 * and how it is created
 */
public class EmergencySimulation extends Simulation {

	private String name;

	private int nbPhysicians, nbNurses, nbTransporters;
	private int nbBoxRoom, nbShockRoom, nbBloodRoom, nbMRIRoom, nbRadioRoom;
	private double simPeriod = 1000;

	//Those variables below will resolve the problem of allocation of resources
	//for a given time (trackTime). They are necessary for updating Event Queue.
	
	private double trackTime = 0;
	static int RegResourceReservedAtT, ShockInstResourceReservedAtT, BoxInstResourceReservedAtT;
	static int ConsulResourceReservedAtT, TranspResourceReservedAtT;
	static int MriResourceReservedAtT, BloodResourceReservedAtT, RadioResourceReservedAtT;

	private ArrayList<Double> LOS_L1 = new ArrayList<Double>();
	private ArrayList<Double> LOS_L2 = new ArrayList<Double>();
	private ArrayList<Double> LOS_L3 = new ArrayList<Double>();
	private ArrayList<Double> LOS_L4 = new ArrayList<Double>();
	private ArrayList<Double> LOS_L5 = new ArrayList<Double>();
	private ArrayList<Double> DTDT_L1 = new ArrayList<Double>();
	private ArrayList<Double> DTDT_L2 = new ArrayList<Double>();
	private ArrayList<Double> DTDT_L3 = new ArrayList<Double>();
	private ArrayList<Double> DTDT_L4 = new ArrayList<Double>();
	private ArrayList<Double> DTDT_L5 = new ArrayList<Double>();

	private ArrayList<Patient> patientsInSimulation = new ArrayList<Patient>();

	private Head patientsWaitingForRegistration = new Head();
	private Head patientsWaitingForShockInstallation = new Head();
	private Head patientsWaitingForBoxInstallation = new Head();
	private Head patientsWaitingForConsultation = new Head();
	private Head patientsWaitingForTransportation = new Head();
	private Head patientsWaitingForBloodTest = new Head();
	private Head patientsWaitingForMRITest = new Head();
	private Head patientsWaitingForRadioTest = new Head();

	private Head idlePhysicians = new Head();
	private Head idleNurses = new Head();
	private Head idleTransporters = new Head();
	private Head emptyBoxRoom = new Head();
	private Head emptyShockRoom = new Head();
	private Head emptyBloodRoom = new Head();
	private Head emptyMRIRoom = new Head();
	private Head emptyRadioRoom = new Head();

	private RandomDist consultationDist = new UniformDist(5, 20);
	private RandomDist bloodDist = new UniformDist(15, 90);
	private RandomDist mriDist = new UniformDist(30, 70);
	private RandomDist radioDist = new UniformDist(10, 20);
	private RandomDist l1Dist = new UniformDist(60, 70);
	private RandomDist l2Dist = new UniformDist(50, 60);
	private RandomDist l3Dist = new UniformDist(40, 50);
	private RandomDist l4Dist = new UniformDist(20, 40);
	private RandomDist l5Dist = new UniformDist(0, 20);

	private SortingPatients sort = new SeveritySorting();

	int nbPatients, maxLength;
	double throughTime;
	long startTime = System.currentTimeMillis();
	/**
	 * EmergencySimulation constructor with no specified parameters
	 */
	public EmergencySimulation() {
	};
	/**
	 * EmergencySimulation constructor specifying the simulation time duration and all of the
	 * ED's resources
	 * @param simPeriod the time in which patients can arrive to the ED
	 * @param nbPhysicians number of physicians
	 * @param nbNurses number of nurses
	 * @param nbTransporters number of transporters
	 * @param nbBoxRoom number of box rooms
	 * @param nbShockRoom number of shock rooms
	 * @param nbBloodRoom number of blood rooms
	 * @param nbMRIRoom number of MRI rooms
	 * @param nbRadioRoom number of radio rooms
	 */
	public EmergencySimulation(double simPeriod, int nbPhysicians, int nbNurses, int nbTransporters, int nbBoxRoom,
			int nbShockRoom, int nbBloodRoom, int nbMRIRoom, int nbRadioRoom) {
		this.simPeriod = simPeriod;

		this.nbPhysicians = nbPhysicians;
		this.nbNurses = nbNurses;
		this.nbTransporters = nbTransporters;
		this.nbBoxRoom = nbBoxRoom;
		this.nbShockRoom = nbShockRoom;
		this.nbBloodRoom = nbBloodRoom;
		this.nbMRIRoom = nbMRIRoom;
		this.nbRadioRoom = nbRadioRoom;

		for (int i = 1; i <= nbPhysicians; i++) {
			new Physician().into(idlePhysicians);
		}

		for (int i = 1; i <= nbNurses; i++) {
			new Nurse().into(idleNurses);
		}

		for (int i = 1; i <= nbTransporters; i++) {
			new Transporter().into(idleTransporters);
		}

		for (int i = 1; i <= nbBoxRoom; i++) {
			new BoxRoom().into(emptyBoxRoom);
		}

		for (int i = 1; i <= nbShockRoom; i++) {
			new ShockRoom().into(emptyShockRoom);
		}

		for (int i = 1; i <= nbBloodRoom; i++) {
			new BloodTestRoom().into(emptyBloodRoom);
		}

		for (int i = 1; i <= nbMRIRoom; i++) {
			new MRIRoom().into(emptyMRIRoom);
		}

		for (int i = 1; i <= nbRadioRoom; i++) {
			new RadioRoom().into(emptyRadioRoom);
		}

	}
	
	
	/**
	 * Sets the arrival of five patients, each of them having a different severity level.
	 * Their time arrival is given by specific distribution probabilites.
	 */
	public void setStochasticArrivals(){
		new PatientArrivalL1(this).schedule(this.l1Dist.getSample());
		new PatientArrivalL2(this).schedule(this.l2Dist.getSample());
		new PatientArrivalL3(this).schedule(this.l3Dist.getSample());
		new PatientArrivalL4(this).schedule(this.l4Dist.getSample());
		new PatientArrivalL5(this).schedule(this.l5Dist.getSample());
	}
	/**
	 * Runs a simulation till the end and reports.
	 */
	public void runSimulation() {
		runSimulation(simPeriod + 10000000000000000000f);
		report();
	}
	/**
	 * Reports the simulation KPIs
	 */
	public void report() {
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
		System.out.println("Simulation completed");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
		System.out.println("Average Door To Doctor Time for L1 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgDTDT_L1()));
		System.out.println("Average Door To Doctor Time for L2 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgDTDT_L2()));
		System.out.println("Average Door To Doctor Time for L3 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgDTDT_L3()));
		System.out.println("Average Door To Doctor Time for L4 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgDTDT_L4()));
		System.out.println("Average Door To Doctor Time for L5 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgDTDT_L5()));
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
		System.out.println("Average Length Of Stay for L1 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgLOS_L1()));
		System.out.println("Average Length Of Stay for L2 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgLOS_L2()));
		System.out.println("Average Length Of Stay for L3 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgLOS_L3()));
		System.out.println("Average Length Of Stay for L4 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgLOS_L4()));
		System.out.println("Average Length Of Stay for L5 Patients : "
				+ new DecimalFormat("#.##").format(this.computeAvgLOS_L5()));
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}

	public double getSimPeriod() {
		return this.simPeriod;
	}

	public Head getPatientsWaitingForRegistration() {
		return this.patientsWaitingForRegistration;
	}

	public Head getPatientsWaitingForShockInstallation() {
		return this.patientsWaitingForShockInstallation;
	}

	public Head getPatientsWaitingForBoxInstallation() {
		return this.patientsWaitingForBoxInstallation;
	}

	public Head getPatientsWaitingForConsultation() {
		return this.patientsWaitingForConsultation;
	}

	public Head getPatientsWaitingForTransportation() {
		return this.patientsWaitingForTransportation;
	}

	public Head getPatientsWaitingForBloodTest() {
		return this.patientsWaitingForBloodTest;
	}

	public Head getPatientsWaitingForMRITest() {
		return this.patientsWaitingForMRITest;
	}

	public Head getPatientsWaitingForRadioTest() {
		return this.patientsWaitingForRadioTest;
	}

	public Head getIdleNurses() {
		return this.idleNurses;
	}

	public Head getIdleTransporters() {
		return this.idleTransporters;
	}

	public Head getIdlePhysicians() {
		return this.idlePhysicians;
	}

	public Head getEmptyBoxRooms() {
		return this.emptyBoxRoom;
	}

	public Head getEmptyBloodRooms() {
		return this.emptyBloodRoom;
	}

	public Head getEmptyMRIRooms() {
		return this.emptyMRIRoom;
	}

	public Head getEmptyRadioRooms() {
		return this.emptyRadioRoom;
	}

	public RandomDist getConsultationDist() {
		return consultationDist;
	}

	public void setConsultationDist(RandomDist consultationDist) {
		this.consultationDist = consultationDist;
	}

	public RandomDist getBloodDist() {
		return bloodDist;
	}

	public void setBloodDist(RandomDist bloodDist) {
		this.bloodDist = bloodDist;
	}

	public RandomDist getMriDist() {
		return mriDist;
	}

	public void setMriDist(RandomDist mriDist) {
		this.mriDist = mriDist;
	}

	public RandomDist getRadioDist() {
		return radioDist;
	}

	public void setRadioDist(RandomDist radioDist) {
		this.radioDist = radioDist;
	}

	public RandomDist getL1Dist() {
		return l1Dist;
	}

	public void setL1Dist(RandomDist l1Dist) {
		this.l1Dist = l1Dist;
	}

	public RandomDist getL2Dist() {
		return l2Dist;
	}

	public void setL2Dist(RandomDist l2Dist) {
		this.l2Dist = l2Dist;
	}

	public RandomDist getL3Dist() {
		return l3Dist;
	}

	public void setL3Dist(RandomDist l3Dist) {
		this.l3Dist = l3Dist;
	}

	public RandomDist getL4Dist() {
		return l4Dist;
	}

	public void setL4Dist(RandomDist l4Dist) {
		this.l4Dist = l4Dist;
	}

	public RandomDist getL5Dist() {
		return l5Dist;
	}

	public void setL5Dist(RandomDist l5Dist) {
		this.l5Dist = l5Dist;
	}

	public ArrayList<Double> getLOS_L1() {
		return LOS_L1;
	}

	public void setLOS_L1(ArrayList<Double> lOS_L1) {
		LOS_L1 = lOS_L1;
	}

	public ArrayList<Double> getLOS_L2() {
		return LOS_L2;
	}

	public void setLOS_L2(ArrayList<Double> lOS_L2) {
		LOS_L2 = lOS_L2;
	}

	public ArrayList<Double> getLOS_L3() {
		return LOS_L3;
	}

	public void setLOS_L3(ArrayList<Double> lOS_L3) {
		LOS_L3 = lOS_L3;
	}

	public ArrayList<Double> getLOS_L4() {
		return LOS_L4;
	}

	public void setLOS_L4(ArrayList<Double> lOS_L4) {
		LOS_L4 = lOS_L4;
	}

	public ArrayList<Double> getLOS_L5() {
		return LOS_L5;
	}

	public void setLOS_L5(ArrayList<Double> lOS_L5) {
		LOS_L5 = lOS_L5;
	}

	public ArrayList<Double> getDTDT_L1() {
		return DTDT_L1;
	}

	public void setDTDT_L1(ArrayList<Double> dTDT_L1) {
		DTDT_L1 = dTDT_L1;
	}

	public ArrayList<Double> getDTDT_L2() {
		return DTDT_L2;
	}

	public void setDTDT_L2(ArrayList<Double> dTDT_L2) {
		DTDT_L2 = dTDT_L2;
	}

	public ArrayList<Double> getDTDT_L3() {
		return DTDT_L3;
	}

	public void setDTDT_L3(ArrayList<Double> dTDT_L3) {
		DTDT_L3 = dTDT_L3;
	}

	public ArrayList<Double> getDTDT_L4() {
		return DTDT_L4;
	}

	public void setDTDT_L4(ArrayList<Double> dTDT_L4) {
		DTDT_L4 = dTDT_L4;
	}

	public ArrayList<Double> getDTDT_L5() {
		return DTDT_L5;
	}

	public void setDTDT_L5(ArrayList<Double> dTDT_L5) {
		DTDT_L5 = dTDT_L5;
	}

	public SortingPatients getSort() {
		return sort;
	}

	public void setSort(SortingPatients sort) {
		this.sort = sort;
	}

	public Head getEmptyShockRooms() {
		return emptyShockRoom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Patient> getPatientsInSimulation() {
		return patientsInSimulation;
	}

	public double getTime() {
		return time();
	}

	public int getNbPhysicians() {
		return nbPhysicians;
	}

	public int getNbNurses() {
		return nbNurses;
	}

	public int getNbTransporters() {
		return nbTransporters;
	}

	public int getNbBoxRoom() {
		return nbBoxRoom;
	}

	public int getNbShockRoom() {
		return nbShockRoom;
	}

	public int getNbBloodRoom() {
		return nbBloodRoom;
	}

	public int getNbMRIRoom() {
		return nbMRIRoom;
	}

	public int getNbRadioRoom() {
		return nbRadioRoom;
	}
	/**
	 * Computes the average LOS KPI for L1 patients
	 * @return average LOS for L1
	 */
	public double computeAvgLOS_L1() {
		double avg = 0;
		for (int i = 0; i < this.LOS_L1.size(); i++) {
			avg += LOS_L1.get(i);
		}
		return avg / this.LOS_L1.size();
	}
	/**
	 * Computes the average LOS KPI for L2 patients
	 * @return average LOS for L2
	 */
	public double computeAvgLOS_L2() {
		double avg = 0;
		for (int i = 0; i < this.LOS_L2.size(); i++) {
			avg += LOS_L2.get(i);
		}
		return avg / this.LOS_L2.size();
	}
	/**
	 * Computes the average LOS KPI for L3 patients
	 * @return average LOS for L3
	 */
	public double computeAvgLOS_L3() {
		double avg = 0;
		for (int i = 0; i < this.LOS_L3.size(); i++) {
			avg += LOS_L3.get(i);
		}
		return avg / this.LOS_L3.size();
	}
	/**
	 * Computes the average LOS KPI for L4 patients
	 * @return average LOS for L4
	 */
	public double computeAvgLOS_L4() {
		double avg = 0;
		for (int i = 0; i < this.LOS_L4.size(); i++) {
			avg += LOS_L4.get(i);
		}
		return avg / this.LOS_L4.size();
	}
	/**
	 * Computes the average LOS KPI for L5 patients
	 * @return average LOS for L5
	 */
	public double computeAvgLOS_L5() {
		double avg = 0;
		for (int i = 0; i < this.LOS_L5.size(); i++) {
			avg += LOS_L5.get(i);
		}
		return avg / this.LOS_L5.size();
	}
	/**
	 * Computes the average DTDT KPI for L1 patients
	 * @return average DTDT for L1
	 */
	public double computeAvgDTDT_L1() {
		double avg = 0;
		for (int i = 0; i < this.DTDT_L1.size(); i++) {
			avg += DTDT_L1.get(i);
		}
		return avg / this.DTDT_L1.size();
	}
	/**
	 * Computes the average DTDT KPI for L2 patients
	 * @return average DTDT for L2
	 */
	public double computeAvgDTDT_L2() {
		double avg = 0;
		for (int i = 0; i < this.DTDT_L2.size(); i++) {
			avg += DTDT_L2.get(i);
		}
		return avg / this.DTDT_L2.size();
	}
	/**
	 * Computes the average DTDT KPI for L3 patients
	 * @return average DTDT for L3
	 */
	public double computeAvgDTDT_L3() {
		double avg = 0;
		for (int i = 0; i < this.DTDT_L3.size(); i++) {
			avg += DTDT_L3.get(i);
		}
		return avg / this.DTDT_L3.size();
	}
	/**
	 * Computes the average DTDT KPI for L4 patients
	 * @return average DTDT for L4
	 */
	public double computeAvgDTDT_L4() {
		double avg = 0;
		for (int i = 0; i < this.DTDT_L4.size(); i++) {
			avg += DTDT_L4.get(i);
		}
		return avg / this.DTDT_L4.size();
	}
	/**
	 * Computes the average DTDT KPI for L5 patients
	 * @return average DTDT for L5
	 */
	public double computeAvgDTDT_L5() {
		double avg = 0;
		for (int i = 0; i < this.DTDT_L5.size(); i++) {
			avg += DTDT_L5.get(i);
		}
		return avg / this.DTDT_L5.size();
	}

	public Head getEmptyInstallationRooms(Patient patient) {
		if (patient.getSeverityLevel() == 1 || patient.getSeverityLevel() == 2) {
			return this.emptyShockRoom;
		} else
			return this.emptyBoxRoom;
	}

	public Head getEmptyExaminationRooms(Patient patient) {
		if (patient.getExamRequired().equals("blood")) {
			return this.emptyBloodRoom;
		} else if (patient.getExamRequired().equals("radio")) {
			return this.emptyRadioRoom;
		} else if (patient.getExamRequired().equals("mri")) {
			return this.emptyMRIRoom;
		}
		return null;
	}

	public Head getWaitingExaminationRooms(Patient patient) {
		if (patient.getExamRequired().equals("blood")) {
			return this.emptyBloodRoom;
		} else if (patient.getExamRequired().equals("radio")) {
			return this.emptyRadioRoom;
		} else if (patient.getExamRequired().equals("mri")) {
			return this.emptyMRIRoom;
		}
		return null;
	}
	/**
	 * Updates the event queue of the simulation
	 * by starting the events that need to be started, 
	 * regarding the resources availability.
	 */
	public void updateEventQueue() {
		if (time() > trackTime) {
			RegResourceReservedAtT = ShockInstResourceReservedAtT = BoxInstResourceReservedAtT = 0;
			ConsulResourceReservedAtT = TranspResourceReservedAtT = 0;
			MriResourceReservedAtT = BloodResourceReservedAtT = RadioResourceReservedAtT = 0;
			trackTime = time();
		}

		int limitingFactorForShInstall = Math.min(this.idleNurses.cardinal(),
				this.patientsWaitingForShockInstallation.cardinal());
		limitingFactorForShInstall = Math.min(limitingFactorForShInstall, this.emptyShockRoom.cardinal());
		if (!this.idleNurses.empty() && !this.patientsWaitingForShockInstallation.empty()
				&& !this.emptyShockRoom.empty() && ShockInstResourceReservedAtT < limitingFactorForShInstall) {
			RegResourceReservedAtT += 1;
			ShockInstResourceReservedAtT += 1;
			BoxInstResourceReservedAtT += 1;
			new StartShockInstallation(this).schedule(time());
		}

		int limitingFactorForBoInstall = Math.min(this.idleNurses.cardinal(),
				this.patientsWaitingForBoxInstallation.cardinal());
		limitingFactorForBoInstall = Math.min(limitingFactorForBoInstall, this.emptyBoxRoom.cardinal());
		if (!this.idleNurses.empty() && !this.patientsWaitingForBoxInstallation.empty() && !this.emptyBoxRoom.empty()
				&& BoxInstResourceReservedAtT < limitingFactorForBoInstall) {
			RegResourceReservedAtT += 1;
			ShockInstResourceReservedAtT += 1;
			BoxInstResourceReservedAtT += 1;
			new StartBoxInstallation(this).schedule(time());
		}

		int limitingFactorForReg = Math.min(this.idleNurses.cardinal(), this.patientsWaitingForRegistration.cardinal());
		if (!this.idleNurses.empty() && !this.patientsWaitingForRegistration.empty()
				&& RegResourceReservedAtT < limitingFactorForReg) {
			RegResourceReservedAtT += 1;
			ShockInstResourceReservedAtT += 1;
			BoxInstResourceReservedAtT += 1;
			new StartRegistration(this).schedule(time());
		}
		
		int limitingFactorForConsult = Math.min(this.idlePhysicians.cardinal(),
				this.patientsWaitingForConsultation.cardinal());
		if (!this.idlePhysicians.empty() && !this.patientsWaitingForConsultation.empty()
				&& ConsulResourceReservedAtT < limitingFactorForConsult) {
			ConsulResourceReservedAtT += 1;
			new StartConsultation(this).schedule(time());
		}

		int limitingFactorForTransp = Math.min(this.idleTransporters.cardinal(),
				this.patientsWaitingForTransportation.cardinal());
		if (!this.idleTransporters.empty() && !this.patientsWaitingForTransportation.empty()
				&& TranspResourceReservedAtT < limitingFactorForTransp) {
			TranspResourceReservedAtT += 1;
			new StartTransportation(this).schedule(time());
		}

		int limitingFactorForMRI = Math.min(this.emptyMRIRoom.cardinal(), this.patientsWaitingForMRITest.cardinal());
		if (!this.patientsWaitingForMRITest.empty() && !this.getEmptyMRIRooms().empty()
				&& MriResourceReservedAtT < limitingFactorForMRI) {
			MriResourceReservedAtT += 1;
			new StartMRIExamination(this).schedule(time());
		}

		int limitingFactorForBlood = Math.min(this.emptyBloodRoom.cardinal(),
				this.patientsWaitingForBloodTest.cardinal());
		if (!this.patientsWaitingForBloodTest.empty() && !this.getEmptyBloodRooms().empty()
				&& BloodResourceReservedAtT < limitingFactorForBlood) {
			BloodResourceReservedAtT += 1;
			new StartBloodExamination(this).schedule(time());
		}

		int limitingFactorForRadio = Math.min(this.emptyRadioRoom.cardinal(),
				this.patientsWaitingForRadioTest.cardinal());
		if (!this.patientsWaitingForRadioTest.empty() && !this.getEmptyRadioRooms().empty()
				&& RadioResourceReservedAtT < limitingFactorForRadio) {
			RadioResourceReservedAtT += 1;
			new StartRadioExamination(this).schedule(time());
		}
	}
	
	@Override
	public String toString() {
		String s = "Number of idle physicians : " + this.idlePhysicians.cardinal() + "\n";
		s += "Number of idle nurses : " + this.idleNurses.cardinal() + "\n";
		s += "Number of idle transporters : " + this.idleTransporters.cardinal() + "\n";
		s += "Number of idle Box Rooms : " + this.emptyBoxRoom.cardinal() + "\n";
		s += "Number of idle Shock Rooms : " + this.emptyShockRoom.cardinal() + "\n";
		s += "Number of idle Blood Rooms : " + this.emptyBloodRoom.cardinal() + "\n";
		s += "Number of idle Radio Rooms : " + this.emptyRadioRoom.cardinal() + "\n";
		s += "Number of idle MRI Rooms : " + this.emptyMRIRoom.cardinal() + "\n";
		s += "--------------------------------\n";
		s += "Patients Waiting For Registration : " + this.patientsWaitingForRegistration.cardinal() + "\n";
		s += "Patients Waiting For Shock Installation : " + this.patientsWaitingForShockInstallation.cardinal() + "\n";
		s += "Patients Waiting For Box Installation : " + this.patientsWaitingForBoxInstallation.cardinal() + "\n";
		s += "Patients Waiting For Consultation : " + this.patientsWaitingForConsultation.cardinal() + "\n";
		s += "Patients Waiting For Blood : " + this.patientsWaitingForBloodTest.cardinal() + "\n";
		s += "Patients Waiting For Radio : " + this.patientsWaitingForRadioTest.cardinal() + "\n";
		s += "Patients Waiting For MRI : " + this.patientsWaitingForMRITest.cardinal();

		return s;
	}

	public void setSimPeriod(double simPeriod) {
		this.simPeriod = simPeriod;
	}

	public int getNbPatients() {
		return nbPatients;
	}

	public void setNbPatients(int nbPatients) {
		this.nbPatients = nbPatients;
	}

	public void setNbPhysicians(int nbPhysicians) {
		this.nbPhysicians = nbPhysicians;
		for (int i = 1; i <= nbPhysicians; i++) {
			new Physician().into(idlePhysicians);
		}

	}

	public void setNbNurses(int nbNurses) {
		this.nbNurses = nbNurses;
		for (int i = 1; i <= nbNurses; i++) {
			new Nurse().into(idleNurses);
		}
	}

	public void setNbBoxRoom(int nbBoxRoom) {
		this.nbBoxRoom = nbBoxRoom;
		for (int i = 1; i <= nbBoxRoom; i++) {
			new BoxRoom().into(emptyBoxRoom);
		}
	}

	public void setNbShockRoom(int nbShockRoom) {
		this.nbShockRoom = nbShockRoom;
		for (int i = 1; i <= nbShockRoom; i++) {
			new ShockRoom().into(emptyShockRoom);
		}
	}

	public void setNbBloodRoom(int nbBloodRoom) {
		this.nbBloodRoom = nbBloodRoom;
		for (int i = 1; i <= nbBloodRoom; i++) {
			new BloodTestRoom().into(emptyBloodRoom);
		}
	}

	public void setNbMRIRoom(int nbMRIRoom) {
		this.nbMRIRoom = nbMRIRoom;
		for (int i = 1; i <= nbMRIRoom; i++) {
			new MRIRoom().into(emptyMRIRoom);
		}
	}

	public void setNbRadioRoom(int nbRadioRoom) {
		this.nbRadioRoom = nbRadioRoom;
		for (int i = 1; i <= nbRadioRoom; i++) {
			new RadioRoom().into(emptyRadioRoom);
		}
	}

	public void setNbTransporters(int nbTransporters) {
		this.nbTransporters = nbTransporters;
		for (int i = 1; i <= nbTransporters; i++) {
			new Transporter().into(idleTransporters);
		}
	}

}
