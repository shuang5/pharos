package org.renci.pharos.gui;

import javax.swing.JOptionPane;

public class GUIPrefMenuItem extends GUIMenuItem{


	public GUIPrefMenuItem() {
		super("Preferences...");
	}

	@Override
	public void perform() {	
		PreferencePanel prefPanel=new PreferencePanel();
		int result = JOptionPane.showConfirmDialog(null, prefPanel, 
                "Preferences", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
        }
	}
	
}
