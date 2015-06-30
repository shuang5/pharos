package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

class GUILabelUsageMenuItem extends GUIMenuItem implements GUICheckboxMenuItem{

	public GUILabelUsageMenuItem() {
		item=new JRadioButtonMenuItem("Label Usage");
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}

	@Override
	public void perform() {
		CHOICES.setObjective(Objectives.LabelUsage);
	}
	public JRadioButtonMenuItem getButton(){
		return (JRadioButtonMenuItem) item;
	}
}