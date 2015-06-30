package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class PopupVisibleEvent implements Event{
	MouseEvent e;
	@Override
	public boolean test(MouseEvent e) {
		return GUIMain.nodePopup.isVisible() || GUIMain.edgePopup1.isVisible()
				|| GUIMain.edgePopup2.isVisible();
	}

	@Override
	public void act(MouseEventType m) {		
		
	}
	
}