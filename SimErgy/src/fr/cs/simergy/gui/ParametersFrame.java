package fr.cs.simergy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
/**
 * Defines the frame in which the GUI client 
 * types the simulation parameters
 *
 */
public class ParametersFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1067465465356L;
	//JFrame components
    private BackgroundPanel panel1 = new BackgroundPanel(new ImageIcon("med_background_1.jpg").getImage());
    private JLabel consultRoom = new JLabel("Consultation Rooms Settings");
    private JLabel boxLabel = new JLabel("Box Rooms:");
    private JLabel shockLabel = new JLabel("Shock Rooms:");
    private JLabel testRoom = new JLabel("Medical Test Rooms Settings");
    private JLabel bloodLabel = new JLabel("Blood test Rooms:");
    private JLabel mriLabel = new JLabel("MRI Rooms:");
    private JLabel radioLabel = new JLabel("Radio Rooms:");
    private JLabel simDuration = new JLabel("Simulation Duration");
    private JTextField boxField = new JTextField("Integer number of box rooms...",20);
    private JTextField shockField = new JTextField("Integer number of shock rooms...",20);
    private JTextField bloodField = new JTextField("Integer number of blood test rooms...",20);
    private JTextField mriField = new JTextField("Integer number of MRI rooms...",20);
    private JTextField radioField = new JTextField("Integer number of Radio rooms...",20);
    private JLabel hResources = new JLabel("Human Resources");
    private JLabel physicians = new JLabel("Physicians:");
    private JLabel nurses = new JLabel("Nurses:");
    private JLabel transporters = new JLabel("Transporters:");
    private JLabel simulationTime = new JLabel("Time Period:");
    private JTextField phyField = new JTextField("Integer number of physicians...",20);
    private JTextField nuField = new JTextField("Integer number of nurses...",20);
    private JTextField trField = new JTextField("Integer number of transporters...",20);
    private JTextField timeField = new JTextField("Integer number for the duration...",20);
    private JButton nextBttn = new JButton("Next");
    private JButton cancelBttn = new JButton("Cancel");
    //Simulation Attributes to store typed information
    private static int boxRoomsNb;
    private static int shockRoomsNb;
    private static int bloodRoomsNb;
    private static int mriRoomsNb;
    private static int radioRoomsNb;
    private static int physiciansNb;
    private static int nursesNb;
    private static int transportersNb;
    private static int simTime;
    
    /**
     * Constructs the ParametersFrame
     */
	public ParametersFrame(){
		//Setting the frame main characteristics
		this.setTitle("SimErgy Simulation");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		this.getContentPane().setLayout(new MigLayout());
	    //Allows to make JTextFields empty when they have the focus
		this.setInvisibleTextField(boxField);
		this.setInvisibleTextField(bloodField);
		this.setInvisibleTextField(mriField);
		this.setInvisibleTextField(radioField);
		this.setInvisibleTextField(shockField);
		this.setInvisibleTextField(nuField);
		this.setInvisibleTextField(phyField);
		this.setInvisibleTextField(trField);
		this.setInvisibleTextField(timeField);
		//Adding Actions Listeners to the buttons
        nextBttn.addActionListener(this);
        cancelBttn.addActionListener(this);
        //Adding components to the panel
        panel1.add(consultRoom, "span, center, gaptop 15, gapbottom 15");
        panel1.add(boxLabel, "align label");
        panel1.add(boxField,"wrap");
        panel1.add(shockLabel,"align label");
        panel1.add(shockField,"wrap");	    
        panel1.add(testRoom,"span, center, gaptop 15, gapbottom 15");
        panel1.add(bloodLabel,"align label");
        panel1.add(bloodField,"wrap");
        panel1.add(mriLabel,"align label");
        panel1.add(mriField,"wrap");
        panel1.add(radioLabel,"align label");
        panel1.add(radioField,"wrap");
        panel1.add(hResources, "span, center, gaptop15,  gapbottom 15");
        panel1.add(physicians, "align label");
        panel1.add(phyField,"wrap");
        panel1.add(nurses,"align label");
        panel1.add(nuField,"wrap");	    
        panel1.add(transporters,"align label");
        panel1.add(trField,"wrap");
        panel1.add(simDuration, "span, center, gaptop 15, gapbottom 15");
        panel1.add(simulationTime,"align label");
        panel1.add(timeField,"wrap");
        panel1.add(nextBttn, "tag next, span, split 3, sizegroup bttn");
        panel1.add(cancelBttn, "tag cancel, sizegroup bttn");
        nextBttn.addActionListener(this);
        cancelBttn.addActionListener(this);
	    //Adding the panel1 to the OutputFrame
	    this.getContentPane().add(panel1);
	    	    
	    this.pack();
		this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    //Putting the initial focus on a component that is not a JTextField to make 
	    //sure all that all the text fields instructions are visible
	    consultRoom.requestFocus();
	  }
	
	/**
	 * Makes a JTextField empty when it gains the focus
	 */
	public void setInvisibleTextField(JTextField field){
		field.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
	               // TODO Auto-generated method stub
	        }
            @Override
            public void focusGained(FocusEvent arg0) {
            	field.setText("");
               
            }
       });
	}
	
	/**
	 * This method allows to convert a string to an integer when it is possible.
	 * Plus, it throws an NumberFormatException when the integer is negative.
	 * @return Integer
	 * @throws NumberFormatException
	 */
	public Integer stringToInt(String text) throws NumberFormatException{
		Integer result = Integer.parseInt(text);
		if(result <= 0){
			throw new NumberFormatException();
		}
		else{
			return result;
		}
	}
	
	
	/**
	 * The "Cancel" button closes the actual frame and opens a new WelcomeFrame to start again.
	 * The "Next" buttons allows to display the simulation result.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancelBttn){
			this.dispose();
			new WelcomeFrame();
    	}
    	else if (e.getSource() == nextBttn){
    		
    		try{     
    			ParametersFrame.boxRoomsNb = stringToInt(boxField.getText());  
        	    ParametersFrame.shockRoomsNb = stringToInt(shockField.getText());
        	    ParametersFrame.bloodRoomsNb = stringToInt(bloodField.getText());
        	    ParametersFrame.mriRoomsNb = stringToInt(mriField.getText());
        	    ParametersFrame.radioRoomsNb = stringToInt(radioField.getText());
        	    ParametersFrame.physiciansNb = stringToInt(phyField.getText());
        	    ParametersFrame.nursesNb = stringToInt(nuField.getText());
        	    ParametersFrame.transportersNb = stringToInt(trField.getText());
        	    ParametersFrame.simTime = stringToInt(timeField.getText());       	    
        	    OutputFrame.getOutputFrame();
        	    this.dispose();
    		}catch(NumberFormatException ex){
    		    JOptionPane.showMessageDialog(null,"All fields must be filled with positive Integers!","Wrong data type", JOptionPane.ERROR_MESSAGE);
    		}
    		
    	}	
	}	
	
	//Getters
	
	public static int getBoxRoomsNb() {
		return boxRoomsNb;
	}

	public static int getShockRoomsNb() {
		return shockRoomsNb;
	}

	public static int getBloodRoomsNb() {
		return bloodRoomsNb;
	}

	public static int getMriRoomsNb() {
		return mriRoomsNb;
	}

	public static int getRadioRoomsNb() {
		return radioRoomsNb;
	}

	public static int getPhysiciansNb() {
		return physiciansNb;
	}

	public static int getNursesNb() {
		return nursesNb;
	}

	public static int getTransportersNb() {
		return transportersNb;
	}

	public static int getSimTime() {
		return simTime;
	}
		
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable (){public void run() {new ParametersFrame();}});
	}
}
