package org.renci.pharos.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.google.common.base.Preconditions;

abstract public class FlowspacePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Circle node;
	protected JTextField fsField;
	protected JTextField requestField;
	protected JTextArea fsArea;
	protected JScrollPane scroll;
	
	public FlowspacePanel(Circle n) {
		//SpringLayout layout = new SpringLayout();
        //setLayout(new GridLayout(3, 2));	
		node=Preconditions.checkNotNull(n);
		fsField = new JTextField(10);
		requestField = new JTextField(10);
		fsArea = new JTextArea();
		scroll=new JScrollPane (fsArea, 
		  		   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
}
