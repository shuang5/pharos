package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

abstract class GUIMenuItem implements GUIMenuHandler{
	public JMenuItem item;
	public GUIMenuItem(String s){
		item=new JMenuItem(s);
		item.setMnemonic('N');
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform();
			}
		});
	}
	public GUIMenuItem(){}
}