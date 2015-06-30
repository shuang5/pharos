package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

class GUILoadBalancingMenuItem extends GUIMenuItem implements GUICheckboxMenuItem{

	public GUILoadBalancingMenuItem() {
		item=new JRadioButtonMenuItem("Load Balancing");
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}

	@Override
	public void perform() {
		CHOICES.setObjective(Objectives.LoadBalancing);
	}
	public JRadioButtonMenuItem getButton(){
		return (JRadioButtonMenuItem) item;
	}
}