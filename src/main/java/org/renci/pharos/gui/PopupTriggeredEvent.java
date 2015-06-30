package org.renci.pharos.gui;

import java.awt.event.MouseEvent;

class PopupTriggeredEvent implements Event{
	MouseEvent e;
	@Override
	public boolean test(MouseEvent ee) {
		e=ee;
		return e.isPopupTrigger();
	}

	@Override
	public void act(MouseEventType m) {
		if(test(e) && GUIMain.topo.isItemFocused(e.getX(), e.getY())){
			// buttonPan.setIconOn(CHOICES.Select);
			GUIMain.topo.selectItem(e.getX(), e.getY());
			switch (GUIMain.topo.selectedShapeType()) {
			case PortNode:
				GUIMain.nodePopup.show(e.getComponent(), e.getX(), e.getY());
				break;
			case NodeNode:
				GUIMain.nodePopup.show(e.getComponent(), e.getX(), e.getY());
				break;
			case NodeLine:
				GUIMain.edgePopup1.show(e.getComponent(), e.getX(), e.getY());
				break;
			case RequestNodeLine:
				GUIMain.edgePopup2.show(e.getComponent(), e.getX(), e.getY());
				break;
			default:
				break;

			}
		}
		
	}
	
}