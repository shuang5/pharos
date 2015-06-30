package org.renci.pharos.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class mouseMotionAdapter extends MouseMotionAdapter {
	/**
	 * 
	 */
	private final GUIMain guiMain;

	/**
	 * @param guiMain
	 */
	mouseMotionAdapter(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.guiMain.evaluateEvents(e,this.guiMain.draggedEvents,MouseEventType.MouseDragged);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(GUIMain.topo.itemContains(e.getX(), e.getY())!=null){

			if(GUIMain.topo.itemContains(e.getX(), e.getY()).getShapeType()==ShapeType.NodeNode){
				NodeCircle n=(NodeCircle)GUIMain.topo.itemContains(e.getX(), e.getY());
				GUIMain.statusBar.setText("Node ID: "+n.getId());
			}
			else if(GUIMain.topo.itemContains(e.getX(), e.getY()).getShapeType()==ShapeType.NodeLine){
				NodeLine n=(NodeLine)GUIMain.topo.itemContains(e.getX(), e.getY());
				GUIMain.statusBar.setText("Edge ID: "+n.getId());
			}
		}
	}
}