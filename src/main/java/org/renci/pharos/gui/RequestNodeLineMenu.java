package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.google.common.base.Preconditions;

class RequestNodeLineMenu extends LineMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RequestNodeLinePanel edgePanel;
	RequestNodeLineMenu(final TopologyGraph tg){
		super(tg);
		menuListener = new ActionListener() {	      
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand()=="Edit"){
					edgePanel=new RequestNodeLinePanel(topo);
					int result = JOptionPane.showConfirmDialog(null, edgePanel, 
		                    "Edge properties:", JOptionPane.OK_CANCEL_OPTION);
		            if (result == JOptionPane.OK_OPTION) {
		            	RequestNodeLine n=Preconditions.checkNotNull((RequestNodeLine)(topo.getSelectedItem()));
		            	n.setId(edgePanel.getId());
		            	try{
		            		n.setRequest1(edgePanel.getRequest1());
		            		n.setRequest2(edgePanel.getRequest2());
		            	}
		            	catch(NumberFormatException ne){
		            		JOptionPane.showMessageDialog(null, "Requests should be integers!");
		            	}
		            	if(n.configComplete())n.setColor(Drawings.requestConfigedColor);
		            	else 
		            		n.setColor(Drawings.defaultNodeColor);
		            	tg.repaint();
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