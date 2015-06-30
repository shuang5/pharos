package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class NoOverlappingLineModeEvent implements Event{
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	NoOverlappingLineModeEvent(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return GUIMain.cho.isLine() && 
				GUIMain.topo.itemContains(e.getX(), e.getY())==null;
	}

	@Override
	public void act(MouseEventType m) {	
		switch(m){
		case MousePressed:
			if(test(e)){
				GUIMain.topo.unSetSelectedItem();
				Line line= LineFactory.createLine(GUIMain.cho.getMode(), GUIMain.cho.isRequest());;
				line.x1 = line.x2 = e.getX();
				line.y1 = line.y2 = e.getY();
				this.guiMain.createNewItem(line);
				this.guiMain.repaint();
			}
			break;
		case MouseReleased:
			Drawings lastItem = GUIMain.topo.getCurrentItem();

			lastItem.x2 = e.getX();
			lastItem.y2 = e.getY();

			Drawings it = GUIMain.topo.itemContains(lastItem.x2, lastItem.y2);
			if(!GUIMain.topo.hasSelectedItem() && it==null){
				// createNewItem(line);
				// we dont want to create something too small to be seen
				if (Math.sqrt((lastItem.x2 - lastItem.x1)
						* (lastItem.x2 - lastItem.x1)
						+ (lastItem.y2 - lastItem.y1)
						* (lastItem.y2 - lastItem.y1)) <= 5) {

					GUIMain.topo.remove(GUIMain.topo.getCurrentItem());
					GUIMain.topo.setCurrentItem(null);
				}
				if (GUIMain.cho.isRequest()) {
					// if(lastItem.y2<lastItem.y1)
					((RequestNodeLine) lastItem).setControlPoint(
							(lastItem.x1 * 3 + lastItem.x2) / 4,
							(lastItem.y2 * 3 + lastItem.y1) / 4);
				}
			}
			this.guiMain.repaint();
			break;
		default:				
			break;
		}
			
	}
	
}