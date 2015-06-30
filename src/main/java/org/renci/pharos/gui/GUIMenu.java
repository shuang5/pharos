package org.renci.pharos.gui;

import javax.swing.JMenu;

class GUIMenu{
	public JMenu item;
	public GUIMenu(String s){
		item = new JMenu(s);
	}
	public GUIMenu add(GUIMenuItem menu) {
		item.add(menu.item);
		return this;
	}
	
}
