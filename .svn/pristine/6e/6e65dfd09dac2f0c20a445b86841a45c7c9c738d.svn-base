package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

import org.renci.pharos.lp.ComputeMode;

class GUIRandomMenuItem extends GUIMenuItem implements GUICheckboxMenuItem{

	public GUIRandomMenuItem() {
		item=new JRadioButtonMenuItem("Heuristic(Random)");
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}

	@Override
	public void perform() {
		CHOICES.setComputeMode(ComputeMode.Random);
	}
	
	public JRadioButtonMenuItem getButton(){
		return (JRadioButtonMenuItem) item;
	}
}