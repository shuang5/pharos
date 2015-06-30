package org.renci.pharos.gui;


class GUISaveRequestMenuItem extends GUIMenuItem{
	
	public GUISaveRequestMenuItem() {
		super("Save Request");
	}

	@Override
	public void perform() {
		GUIMain.saveRequest();
	}
	
}