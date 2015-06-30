package org.renci.pharos.gui;

class GUIExitMenuItem extends GUIMenuItem{

	public GUIExitMenuItem() {
		super("Exit");
	}

	@Override
	public void perform() {
		System.exit(0);		
	}
	
}