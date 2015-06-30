package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

import org.renci.pharos.lp.ComputeMode;

class GUIShortestFirstMenuItem extends GUIMenuItem implements GUICheckboxMenuItem{

	public GUIShortestFirstMenuItem() {
		item=new JRadioButtonMenuItem("Heuristic(Shortest First)");
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}

	@Override
	public void perform() {
		CHOICES.setComputeMode(ComputeMode.ShortestFirst);
	}
	
	public JRadioButtonMenuItem getButton(){
		return (JRadioButtonMenuItem) item;
	}
}