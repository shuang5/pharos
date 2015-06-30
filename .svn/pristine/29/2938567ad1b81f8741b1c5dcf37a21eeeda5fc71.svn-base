package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class ResizeModeEvent implements Event{
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	ResizeModeEvent(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return GUIMain.cho.isResize();
	}

	@Override
	public void act(MouseEventType m) {	
		switch(m){
		case MousePressed:
			if(test(e))
				GUIMain.topo.selectItem(e.getX(), e.getY());
			break;
		case MouseReleased:
			if (GUIMain.topo.hasSelectedNode()) {
				Drawings item = GUIMain.topo.getSelectedItem();
				int X = e.getX();
				int Y = e.getY();
				if (((X - item.x1) * (X - item.x1) + (Y - item.y1)
						* (Y - item.y1)) <= ((X - item.x2) * (X - item.x2) + (Y - item.y2)
						* (Y - item.y2))) {
					item.x1 = X;
					item.y1 = Y;
				} else {
					item.x2 = X;
					item.y2 = Y;
				}
				this.guiMain.repaint();
			}
			break;
		case MouseDragged:
			Drawings shape = GUIMain.topo.getSelectedItem();
			if (GUIMain.topo.hasSelectedNode()) {
				int X = e.getX();
				int Y = e.getY();
				if (((X - shape.x1) * (X - shape.x1) + (Y - shape.y1)
						* (Y - shape.y1)) <= ((X - shape.x2)
						* (X - shape.x2) + (Y - shape.y2) * (Y - shape.y2))) {
					shape.x1 = X;
					shape.y1 = Y;
				} else {
					shape.x2 = X;
					shape.y2 = Y;
				}
				for (Drawings d : ((Circle) shape).inLinks()) {
					d.x2 = shape.centerX();
					d.y2 = shape.centerY();

				}
				for (Drawings d : ((Circle) shape).outLinks()) {
					d.x1 = shape.centerX();
					d.y1 = shape.centerY();
					
				}
				for (Drawings d : ((Circle) shape).inReqLinks()) {
					d.x2 = shape.centerX();
					d.y2 = shape.centerY();

				}
				for (Drawings d : ((Circle) shape).outReqLinks()) {
					d.x1 = shape.centerX();
					d.y1 = shape.centerY();
					
				}
			}
			this.guiMain.repaint();
			break;
		default:
			break;
		}
	}
	
}