package org.renci.pharos.gui;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PreferencePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel solver=new JLabel("Solver:");
	private JRadioButton local=new JRadioButton("Local");
	private JRadioButton remote=new JRadioButton("Remote");
	private JTextField solverURL=new JTextField(15);
	public PreferencePanel() {
	    initComponents();	    
	}
	private void initComponents() {
		FlowLayout layout = new FlowLayout();
        setLayout(layout);
        ButtonGroup myButtonGroup = new ButtonGroup();
		myButtonGroup.add(local);
		myButtonGroup.add(remote);
        add(solver);
        add(local);
        add(remote);
        add(solverURL);
	}
}
