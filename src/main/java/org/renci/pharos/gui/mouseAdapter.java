package org.renci.pharos.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class mouseAdapter extends MouseAdapter {
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	mouseAdapter(GUIMain guiMain) {
		this.guiMain = guiMain;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		this.guiMain.evaluateEvents(e,this.guiMain.pressedEvents,MouseEventType.MousePressed);

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		this.guiMain.evaluateEvents(e,this.guiMain.releasedEvents,MouseEventType.MouseReleased);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}