package fr.cs.simergy.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
/**
 * Defines the introductory frame that the
 * GUI client sees first
 *
 */
public class WelcomeFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1067465465357L;
	private BackgroundPanel panel1 = new BackgroundPanel(new ImageIcon("med_background_1.jpg").getImage());
    private JButton nextBttn = new JButton("Next");
    private JButton cancelBttn = new JButton("Cancel");
    private JLabel introMessage1 = new JLabel("Welcome to our SimErgy simulation!");
    private JLabel introMessage2 = new JLabel("Click Next to fill the simulation parameters");
    private JLabel introMessage3 = new JLabel("Click Cancel to close the simulator");


	/**
	 * Constructs the first Frame of the GUI
	 */
	public WelcomeFrame(){
		//Setting the frame main characteristics
	    this.setTitle("SimErgy Simulation");
	    this.setSize(700,500);
		this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	    this.getContentPane().setLayout(new MigLayout());
	    //Defining the text police
	    Font police1 = new Font("Segoe UI", Font.BOLD, 30);
	    Font police2 = new Font("Calibri",Font.ITALIC, 18);
	    //Setting the text police
	    introMessage1.setFont(police1);
	    introMessage2.setFont(police2);
	    introMessage3.setFont(police2);
	    //Changing the text color
	    introMessage1.setForeground(Color.BLACK);
	    introMessage2.setForeground(Color.BLACK);
	    introMessage3.setForeground(Color.BLACK);
	  	//Setting the panel size and adding components to it    
	    panel1.setPreferredSize(new Dimension(700, 500));
	    panel1.add(introMessage1, "span, center");
	    panel1.add(introMessage2, "span, center");
	    panel1.add(introMessage3, "span, center");
	    panel1.add(nextBttn, "tag next, span, split 3, sizegroup bttn");
        panel1.add(cancelBttn, "tag cancel, sizegroup bttn");
        //Adding action listener to the two buttons
        nextBttn.addActionListener(this);
        cancelBttn.addActionListener(this);
	    //Adding the panel to the frame
	    this.add(panel1);
	    //Making the frame visible
	    this.setVisible(true);  
	  }
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancelBttn){
			this.dispose();
    	}
    	else if (e.getSource() == nextBttn){
    		this.dispose();
			new ParametersFrame();
    	}
	}
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable (){public void run() {new WelcomeFrame();}});
	}	

}
