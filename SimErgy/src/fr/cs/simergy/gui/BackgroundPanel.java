package fr.cs.simergy.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
/**
 * Allows to set a picture as a JPanel background
 *
 */

public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 1067465465354L;
	private Image img;

	public BackgroundPanel(Image img) {
		this.img = img;
		this.setLayout(new MigLayout("", "[grow]", "[grow]"));
	  }

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
