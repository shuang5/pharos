package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class NodeCircleMenu extends CircleMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodeCirclePanel nodePanel;
	NodeCircleMenu(final TopologyGraph tg){
		super(tg);
		menuListener = new ActionListener() {	      
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand()=="Edit"){
					nodePanel=new NodeCirclePanel(topo);
					nodePanel.getNode();
					int result = JOptionPane.showConfirmDialog(null, nodePanel, 
		                    "Node properties:", JOptionPane.OK_CANCEL_OPTION);
		            if (result == JOptionPane.OK_OPTION) {
		            	nodePanel.setNode();
		            }
				}
				else if(e.getActionCommand()=="Delete"){
					topo.remove(topo.getSelectedItem());
					topo.repaint();
				}
			}
		};
		anItem = new JMenuItem("Edit");
        add(anItem);
        anItem.addActionListener(menuListener);
        anItem = new JMenuItem("Delete");
        add(anItem);
        anItem.addActionListener(menuListener);
	}
}