package org.renci.pharos.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawPanel extends JPanel {
	/**
	 * 
	 */
	private final GUIMain guiMain;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrawPanel(GUIMain guiMain) {
		this.guiMain = guiMain;
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setBackground(Color.white);
		addMouseListener(new mouseAdapter(this.guiMain));
		addMouseMotionListener(new mouseMotionAdapter(this.guiMain));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GUIMain.topo.draw(g);
	}

}