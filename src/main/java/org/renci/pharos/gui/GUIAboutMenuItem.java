package org.renci.pharos.gui;

import javax.swing.JOptionPane;

class GUIAboutMenuItem extends GUIMenuItem{

	public GUIAboutMenuItem() {
		super("About");
	}

	@Override
	public void perform() {
		JOptionPane.showMessageDialog(null,
				"Network virtualization and delegation", "",
				JOptionPane.INFORMATION_MESSAGE);	
	}
	
}