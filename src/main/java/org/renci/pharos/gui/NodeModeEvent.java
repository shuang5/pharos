package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class NodeModeEvent implements Event{
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	NodeModeEvent(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return GUIMain.cho.isNode();
	}

	@Override
	public void act(MouseEventType m) {	
		switch(m){
		case MousePressed:
			GUIMain.topo.unSetSelectedItem();
			Circle node = NodeFactory.createNode(GUIMain.cho.getMode());
			node.x1 = node.x2 = e.getX();
			node.y1 = node.y2 = e.getY();
			this.guiMain.createNewItem(node);
			this.guiMain.repaint();
			break;
		case MouseReleased:
			// draw a new Node
			Drawings lastItem = GUIMain.topo.getCurrentItem();
			lastItem.x2 = e.getX();
			lastItem.y2 = e.getY();
			// createNewItem(node);
			// we dont want to create something too small to be seen
			if (Math.sqrt((lastItem.x2 - lastItem.x1)
					* (lastItem.x2 - lastItem.x1)
					+ (lastItem.y2 - lastItem.y1)
					* (lastItem.y2 - lastItem.y1)) >= 4) {

				GUIMain.topo.attachLines((Circle) lastItem);
			} else {
				GUIMain.topo.remove(lastItem);
			}
			GUIMain.topo.setCurrentItem(null);
			this.guiMain.repaint();
			
			break;
		default:
			break;
		}
			
	}
	
}