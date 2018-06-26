package fr.cs.simergy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import fr.cs.simergy.simulation.EmergencySimulation;
/**
 * Defines the frame in which we display the simulation results
 */
public class OutputFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1067465465355L;
	//JFrame components
    private BackgroundPanel panel1 = new BackgroundPanel(new ImageIcon("med_background_1.jpg").getImage());
	private JLabel resultMessage = new JLabel("Simulation report");
    private JTextArea outputArea = new JTextArea(30, 40);
    private JScrollPane scrollPane = new JScrollPane(outputArea);
	private JButton newSimBttn = new JButton("New Simulation");
	private JButton finishBttn = new JButton("Finish");
	private JTextAreaOutputStream outStream = new JTextAreaOutputStream(outputArea); 
	private JTextAreaOutputStream errStream = new JTextAreaOutputStream(outputArea); 
	//Attributes which allows to create a unique OutputFrame instance within the Singleton Pattern
	private static OutputFrame instance;
	
	/**
	 * Constructs an OutputFrame
	 */
	private OutputFrame(){
		//Setting the frame main characteristics
		this.setTitle("SimErgy Simulation");
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
	    //Reassigning the standard output stream into a JTextArea
	    System.setOut(new PrintStream(outStream)); 
	    System.setErr(new PrintStream(errStream)); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//Makes the output JTextArea ineditable 
	    outputArea.setEditable(false);	
	    //Adding components to the panel
	    panel1.add(resultMessage,"span, center, gapbottom 15");
	    panel1.add(scrollPane, "span, center, gapbottom 15");
	    panel1.add(newSimBttn, "tag next, span, split 3, sizegroup bttn");
        panel1.add(finishBttn, "tag cancel, sizegroup bttn");
        //Adding Action Listeners to the two buttons
        newSimBttn.addActionListener(this);
        finishBttn.addActionListener(this);
	    //Adding the panel1 to the OutputFrame
	    this.getContentPane().add(panel1);
	    
	    this.pack();
		this.setLocationRelativeTo(null);
        //Creating and running an EmergencySimulation instance with the collected data
	    EmergencySimulation emSim = new EmergencySimulation(ParametersFrame.getSimTime(), ParametersFrame.getPhysiciansNb(),  ParametersFrame.getNursesNb(), ParametersFrame.getTransportersNb(), ParametersFrame.getBoxRoomsNb(), 
	    		ParametersFrame.getShockRoomsNb(), ParametersFrame.getBloodRoomsNb(), ParametersFrame.getMriRoomsNb(), ParametersFrame.getRadioRoomsNb());
		emSim.setStochasticArrivals();
	    emSim.runSimulation();
	    //Making the JFrame visible
	    this.setVisible(true);   
	  }
	/**
	 * Method of the Singleton Pattern that allows to instantiate the OutputFrame only once
	 * @return unique OutputFrame
	 */
	public static OutputFrame getOutputFrame(){
		if(instance == null){
			instance = new OutputFrame();	
		}
		return instance;
	}
	/**
	 * The "New Simulation" button allows to create a new simulation with other parameters to chose. 
	 * The "Finish" Button stops the GUI display.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == newSimBttn){
			this.dispose();
			instance = null;
			new ParametersFrame();
    	}
    	else if (e.getSource() == finishBttn){
    		this.dispose();
    	}
	}
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable (){public void run() {new OutputFrame();}});
	}


}
