package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class NodeLineModeEvent implements Event{
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	NodeLineModeEvent(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		if(!GUIMain.cho.isLine())return false;
		Drawings it = GUIMain.topo.itemContains(e.getX(), e.getY());
		if (it==null || it.getShapeType() != ShapeType.NodeNode )return false;
		else return true;
	}

	@Override
	public void act(MouseEventType m) {
		switch(m){
		case MousePressed:
			GUIMain.topo.unSetSelectedItem();
			Drawings it = GUIMain.topo.itemContains(e.getX(), e.getY());
			Line line = LineFactory.createLine(GUIMain.cho.getMode(), GUIMain.cho.isRequest());
			// drawing a nodeline, start within an existing node
			line.x1 = line.x2 = it.centerX();
			line.y1 = line.y2 = it.centerY();
			((NodeLine) line).setNode1((Circle) it);

			this.guiMain.createNewItem(line);
			this.guiMain.repaint();
			((NodeCircle) it).addLinkOut(line);
			break;
		case MouseReleased:
			// draw a new Line
			Drawings lastItem = GUIMain.topo.getCurrentItem();

			lastItem.x2 = e.getX();
			lastItem.y2 = e.getY();

			it = GUIMain.topo.itemContains(lastItem.x2, lastItem.y2);
			// reset to the center of the node
			lastItem.x2 = it.centerX();
			lastItem.y2 = it.centerY();
			((NodeLine) lastItem).setNode2((Circle) it);
			
			// we dont want to create something too small to be seen
			if (Math.sqrt((lastItem.x2 - lastItem.x1)
					* (lastItem.x2 - lastItem.x1)
					+ (lastItem.y2 - lastItem.y1)
					* (lastItem.y2 - lastItem.y1)) >= 5) {

				((NodeCircle) it).addLinkIn((Line) lastItem);
			} else {
				GUIMain.topo.remove(lastItem);
				GUIMain.topo.setCurrentItem(null);
			}
			this.guiMain.repaint();
			break;
		default:
			break;	
		}
	}
	
}