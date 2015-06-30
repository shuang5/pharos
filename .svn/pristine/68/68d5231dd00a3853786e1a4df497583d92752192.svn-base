package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

import org.renci.pharos.lp.ComputeMode;

class GUILpRelaxMenuItem extends GUIMenuItem implements GUICheckboxMenuItem{

	public GUILpRelaxMenuItem() {
		item=new JRadioButtonMenuItem("LP Relaxation");
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}

	@Override
	public void perform() {
		CHOICES.setComputeMode(ComputeMode.LPRelaxation);
	}
	public JRadioButtonMenuItem getButton(){
		return (JRadioButtonMenuItem) item;
	}
}