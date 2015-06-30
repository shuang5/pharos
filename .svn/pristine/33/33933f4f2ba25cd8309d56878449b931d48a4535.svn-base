package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class SelectModeEvent implements Event{
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * @param guiMain
	 */
	SelectModeEvent(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return GUIMain.cho.isSelect();
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
				// move the linked components
				Circle shape = (Circle) GUIMain.topo.getSelectedItem();
				GUIMain.topo.attachLines(shape);
				shape.repositionAttached();
			}
			break;
		case MouseDragged:
			Drawings shape = GUIMain.topo.getSelectedItem();
			if (GUIMain.topo.hasSelectedNode()) {
				// e.getX(), e.getY() is the upper left corner of the shape
				// find the upper left corner first, ie,
				// min(x1,x2),min(y1,y2)
				// set the upper left corner to e.getX(), e.getY()
				// set the lower right corner accordingly

				if (shape.x1 < shape.x2) {
					int d = e.getX() - shape.x1;
					shape.x1 = e.getX();
					shape.x2 = shape.x2 + d;
				} else {
					int d = e.getX() - shape.x2;
					shape.x2 = e.getX();
					shape.x1 = shape.x2 + d;
				}
				if (shape.y1 < shape.y2) {
					int d = e.getY() - shape.y1;
					shape.y1 = e.getY();
					shape.y2 = shape.y2 + d;
				} else {
					int d = e.getY() - shape.y2;
					shape.y2 = e.getY();
					shape.y1 = shape.y1 + d;
				}
				// move the linked components
				GUIMain.topo.attachLines((Circle) shape);

				((Circle) shape).repositionAttached();

			} else if (GUIMain.topo.selectedShapeType() == ShapeType.NodeLine
					|| GUIMain.topo.selectedShapeType() == ShapeType.RequestNodeLine) {
				if (((Line) shape).isXSelected()) {
					shape.x1 = e.getX();
					shape.y1 = e.getY();

					// statusBar.setText("selected point1");
				} else if (((Line) shape).isYSelected()) {
					shape.x2 = e.getX();
					shape.y2 = e.getY();
					// statusBar.setText("selected point2");
				}
			}
			this.guiMain.repaint();
			break;
		default:
			break;
		}
	}
	
}