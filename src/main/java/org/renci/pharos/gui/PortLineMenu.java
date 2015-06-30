package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.google.common.base.Preconditions;

class PortLineMenu extends LineMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PortLinePanel edgePanel;
	PortLineMenu(TopologyGraph tg){
		super(tg);
		edgePanel=new PortLinePanel(tg);
		menuListener = new ActionListener() {	      
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand()=="Edit"){
					PortLine p=Preconditions.checkNotNull((PortLine)(topo.getSelectedItem()));
					edgePanel.setId(p.getId());
					edgePanel.setLabel(p.getLabel());
					edgePanel.setCapabilities(p.getCapabilities());
					int result = JOptionPane.showConfirmDialog(null, edgePanel, 
		                    "Edge properties:", JOptionPane.OK_CANCEL_OPTION);
		            if (result == JOptionPane.OK_OPTION) {
		            	p.setLabel(edgePanel.getLabel());
		            	p.setId(edgePanel.getId());
		            	p.setCapabilities(edgePanel.getCapabilities());
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