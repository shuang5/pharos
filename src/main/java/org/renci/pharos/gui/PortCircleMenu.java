package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.google.common.base.Preconditions;

class PortCircleMenu extends CircleMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PortCirclePanel nodePanel;
	PortCircleMenu(TopologyGraph tg){
		super(tg);
		nodePanel=new PortCirclePanel(tg);
		menuListener = new ActionListener() {	      
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand()=="Edit"){
					PortCircle pn=Preconditions.checkNotNull((PortCircle)topo.getSelectedItem());
					nodePanel.setDomain(pn.portproperties().getDomain());
					nodePanel.setId(pn.portproperties().getId());
					nodePanel.setDPID(pn.portproperties().getDPID());
					nodePanel.setPort(pn.portproperties().getPort());
					//nodePanel.setType(((Node)(topo.getSelectedItemShape())).getType());
					int result = JOptionPane.showConfirmDialog(null, nodePanel, 
		                    "Node properties:", JOptionPane.OK_CANCEL_OPTION);
		            if (result == JOptionPane.OK_OPTION) {
		            	
		            	pn.portproperties().setDomain(nodePanel.getDomain());
		            	pn.portproperties().setId(nodePanel.getId());
		            	pn.portproperties().setDPID(nodePanel.getDPID());
		            	//((Node)(topo.getSelectedItemShape())).setType(nodePanel.getType());
		            	pn.portproperties().setPort(Integer.parseInt(nodePanel.getPort()));
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