package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class CatchAllEvent implements Event{
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	CatchAllEvent(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return true;
	}

	@Override
	public void act(MouseEventType m) {		
		switch(m){
		case MouseDragged:
			if(GUIMain.topo.getCurrentItem()!=null){
				GUIMain.topo.getCurrentItem().x2 = e.getX();
				GUIMain.topo.getCurrentItem().y2 = e.getY();
				this.guiMain.repaint();
			}
			break;
		default:
			break;
		}
	}
	
}