package org.renci.pharos.gui;

class GUINewMenuItem extends GUIMenuItem{

	/**
	 * 
	 */
	private final GUIMain mainGUI;

	public GUINewMenuItem(GUIMain mainGUI) {
		super("New");
		this.mainGUI = mainGUI;
	}

	@Override
	public void perform() {
		this.mainGUI.newFile();			
	}
	
}