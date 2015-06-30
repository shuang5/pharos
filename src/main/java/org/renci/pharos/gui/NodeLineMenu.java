package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


class NodeLineMenu extends LineMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodeEdgePanel edgePanel;
	NodeLineMenu(final TopologyGraph tg){
		super(tg);
		menuListener = new ActionListener() {	      
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand()=="Edit"){
					edgePanel=new NodeEdgePanel(topo);
					edgePanel.getEdge();
					int result = JOptionPane.showConfirmDialog(null, edgePanel, 
		                    "Edge Properties:", JOptionPane.OK_CANCEL_OPTION);
		            if (result == JOptionPane.OK_OPTION) {
		            	edgePanel.setEdge();
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