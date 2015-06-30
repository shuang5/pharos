package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.renci.pharos.flow.FlowUnit;

import com.google.common.base.Preconditions;

class NodeFsPanel extends FlowspacePanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int portSelected=-1;
	NodeFsPanel(Circle n){
		super(Preconditions.checkNotNull(n));
		add(new JLabel("Ports"));
		Vector<Integer> ports=new Vector<Integer>();
		//since we assume links are all bidirectional, number of inlinks = that of outlinks
		for(int i=0;i<n.getNumberOfInLinks();i++){
			ports.add(i);
		}
		JList<?> lst=new JList<Object>(ports);
		lst.addListSelectionListener(
            new ListSelectionListener() {
            	public void valueChanged(ListSelectionEvent e) {
            		JList<?> jl=(JList<?>)e.getSource();
    				portSelected=(Integer)jl.getSelectedValue();
    				fsField.setText(((NodeCircle)node).portproperties(portSelected).flowspace.toString());
    			}
            });
		add(lst);
	    add(new JLabel("Flow Space:"));
	    add(fsField);
	    //add(new JLabel("Mask:"));
	    //add(maskField);
	    JButton addflowunit = new JButton("Add");
	    addflowunit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {    
                    	try{
                    		if(portSelected!=-1){
                    			FlowUnit fu=new FlowUnit(fsField.getText());
                    			((NodeCircle)node).portproperties(portSelected).addFlowSpace(fu);
                    			fsField.setText(((NodeCircle)node).portproperties(portSelected).flowspace.toString());
                    			//fsField.setText(null);
                    		}
                    	}
                    	catch (Exception ex){
                    		JOptionPane.showMessageDialog(null, ex.getMessage());
                    	}
                    }
                });
	    add(addflowunit);
	    
	    JButton andflowspace = new JButton("Remove");
	    andflowspace.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) { 
                    	try{
                    		if(portSelected!=-1){
	                    		FlowUnit fu=new FlowUnit(fsField.getText());
	                    		((NodeCircle)node).portproperties(portSelected).removeFlowSpace(fu);
	                    		fsField.setText(((NodeCircle)node).portproperties(portSelected).flowspace.toString());
	                    		//fsField.setText(null);
                    		}
                    	}
                    	catch (Exception ex){
                    		JOptionPane.showMessageDialog(null, ex.getMessage());
                    	}
                    	 
                    }
                });
	    add(andflowspace);
	    JButton orflowspace = new JButton("Or");
	    orflowspace.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) { 
                    	
                    	JOptionPane.showMessageDialog(null, "Not implemented!");
                    }
                });
	    add(orflowspace);
	   
	}
}