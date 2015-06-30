package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

class GUIPortUsageMenuItem extends GUIMenuItem implements GUICheckboxMenuItem{

	public GUIPortUsageMenuItem() {
		item=new JRadioButtonMenuItem("Port Usage");
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}

	@Override
	public void perform() {
		CHOICES.setObjective(Objectives.PortUsage);
	}
	public JRadioButtonMenuItem getButton(){
		return (JRadioButtonMenuItem) item;
	}
}