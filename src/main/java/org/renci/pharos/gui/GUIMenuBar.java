package org.renci.pharos.gui;

import javax.swing.JMenuBar;

class GUIMenuBar {
	JMenuBar bar = new JMenuBar();
	public GUIMenuBar add(GUIMenu m){
		bar.add(m.item);
		return this;
	}
	
}