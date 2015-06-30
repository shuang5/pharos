package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class LineModeEvent implements Event{
	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return GUIMain.cho.isLine();
	}

	@Override
	public void act(MouseEventType m) {	
		if(test(e))
			GUIMain.topo.selectItem(e.getX(), e.getY());
	}
	
}