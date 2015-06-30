package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

public class PortLinePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private TopologyGraph topo;
	private JTextField idField = new JTextField(5);
	private String[] labels={
    		LinePropertyConstants.Label.is,LinePropertyConstants.Label.can
        };
    private JComboBox<?> lJCB = new JComboBox<Object>(labels);
    
    
    private JCheckBox l2checkbox = new JCheckBox(LinePropertyConstants.Capabilities.L2);
    private JCheckBox vlancheckbox = new JCheckBox(LinePropertyConstants.Capabilities.Vlan);
    private JCheckBox ipcheckbox = new JCheckBox(LinePropertyConstants.Capabilities.IP);
    private JButton jb=new JButton("Rules");
    private JLabel cap=new JLabel("Capabilities:");
    private RulesPanel rp;
	public PortLinePanel(TopologyGraph t) {
		//topo=t;
		rp=new RulesPanel(t);
		//SpringLayout layout = new SpringLayout();
        //setLayout(new GridLayout(2, 2));	
        add(new JLabel("ID:"));
        add(idField);
        
        //add(Box.createVerticalStrut(15)); // a spacer        
        lJCB.setSelectedIndex(-1);
        add(new JLabel("Label:"));
        lJCB.addActionListener(
        		new ActionListener() {
                    public void actionPerformed(ActionEvent e) {                   
        	            if (String.valueOf(lJCB.getSelectedItem()).equals(LinePropertyConstants.Label.is)) {
        	            	l2checkbox.setEnabled(false);
        	            	vlancheckbox.setEnabled(false);
        	            	ipcheckbox.setEnabled(false);
        	            } 
        	            else if (String.valueOf(lJCB.getSelectedItem()).equals(LinePropertyConstants.Label.can)) {
        	            	l2checkbox.setEnabled(true);
        	            	vlancheckbox.setEnabled(true);
        	            	ipcheckbox.setEnabled(true);
        	            } 
        	            
                    }
                });
        add(lJCB);
        add(cap);
        add(l2checkbox);
        add(vlancheckbox);
        add(ipcheckbox);
        //add(Box.createVerticalStrut(15)); // a spacer 
        //add(new JLabel("Rules:"));
        
        jb.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {                    	
                    	int result = JOptionPane.showConfirmDialog(null, rp, 
        	                    "Edge properties:", JOptionPane.OK_CANCEL_OPTION);
        	            if (result == JOptionPane.OK_OPTION) {
        	            }   
                    }
                });
        
        add(jb);
        //add(rulesArea);
        unsetVirtual();
        
	}
	public void setVirtual(){
		l2checkbox.setVisible(false);
		vlancheckbox.setVisible(false);
		ipcheckbox.setVisible(false);
		cap.setVisible(false);
		jb.setVisible(true);
	}
	public void unsetVirtual(){
		l2checkbox.setVisible(true);
		vlancheckbox.setVisible(true);
		ipcheckbox.setVisible(true);
		cap.setVisible(true);
		jb.setVisible(false);
	}
	public String getId(){
		return idField.getText();
	}
	public String getLabel(){
		if (lJCB.getSelectedItem()!=null)
			return lJCB.getSelectedItem().toString();
		else return null;
	}
	public String getCapabilities(){
		String r=new String();
		if (l2checkbox.isSelected()) r=l2checkbox.getText();
		if (vlancheckbox.isSelected()) {
			if(r==null) r=vlancheckbox.getText();
			else r=r+":"+vlancheckbox.getText();
		}
		if (ipcheckbox.isSelected()) {
			if(r==null) r=ipcheckbox.getText();
			else r=r+":"+ipcheckbox.getText();
		}
		return r;
	}
	public void setId(String d){
		idField.setText(d);
	}
	public void setLabel(String d){
		lJCB.setSelectedItem(d);
	}
	public void setCapabilities(String d){	
		l2checkbox.setSelected(false);
		vlancheckbox.setSelected(false);
		ipcheckbox.setSelected(false);
		
		if(!StringUtils.isEmpty(d) && !StringUtils.isBlank(d)) {
			String delims = ":";
			String[] tokens = d.split(delims);
			for (String s : tokens){
				if (s.matches(LinePropertyConstants.Capabilities.L2))l2checkbox.setSelected(true);
				else if (s.matches(LinePropertyConstants.Capabilities.Vlan))vlancheckbox.setSelected(true);
				else if (s.matches(LinePropertyConstants.Capabilities.IP))ipcheckbox.setSelected(true);
			}
		}
		
	}
}
