package fr.cs.simergy.clientinterface;

import javax.swing.SwingUtilities;

import fr.cs.simergy.gui.WelcomeFrame;
/**
 * When you run this class, the GUI is displayed
 *
 */
public class GuiDisplayer {
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable (){public void run() {new WelcomeFrame();}});
	}
}