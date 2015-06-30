package org.renci.pharos.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;


public class NodeCirclePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopologyGraph topo;
	private JTextField idField = new JTextField(5);
    private JTextField domainField = new JTextField(5);
    private JTextField dpidField = new JTextField(5);
    private JCheckBox ltcheckbox = new JCheckBox("Label Translation");
    //private JTextField fvurlField = new JTextField(30);
    
    private String[] types={
    		"flowspace"
    };
    private JComboBox<?> combobox = new JComboBox<Object>(types);
    private JButton fs=new JButton("Flow Space");
    private FlowspacePanel fsp;
	
	public NodeCirclePanel(TopologyGraph t) {
		FlowLayout layout = new FlowLayout();
        setLayout(layout);		
		add(new JLabel("ID:"));
        add(idField);
        //super.add(Box.createHorizontalStrut(15)); // a spacer
        add(new JLabel("Domain:"));
        add(domainField);
        add(new JLabel("DPID:"));
        add(dpidField);
        add(ltcheckbox);
        //add(new JLabel("Flowvisor:"));
        //add(fvurlField);
        combobox.setSelectedIndex(-1);
        //add(new JLabel("Type:"));
        //add(combobox);
        topo=t;
        
        fs.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {     
                    	fsp=new NodeFsPanel(Preconditions.checkNotNull((Circle)topo.getSelectedItem()));
                    	int result = JOptionPane.showConfirmDialog(null, fsp, 
        	                    "Flowspace Panel", JOptionPane.OK_CANCEL_OPTION);
        	            if (result == JOptionPane.OK_OPTION) {
        	            }   
                    }
                });
        
        add(fs);
       
        
	}
	public String getId(){
		return idField.getText();
	}
	public String getDomain(){
		return domainField.getText();
	}
	public String getDPID(){
		return dpidField.getText();
	}
	
	public String getType(){
		return combobox.getSelectedItem().toString();
	}
	
	public void setId(String d){
		if(!Strings.isNullOrEmpty(d))idField.setText(d);
	}
	public void setDomain(String d){
		if(!Strings.isNullOrEmpty(d))domainField.setText(d);
	}
	public void setDPID(String d){
		if(!Strings.isNullOrEmpty(d))dpidField.setText(d);
	}
	
	public void setType(String d){
		if(!Strings.isNullOrEmpty(d))combobox.setSelectedItem(d);
	}
	public boolean isLabelTranslation(){
		return ltcheckbox.isSelected();
	}
	public void setLabelTranslation(boolean b){
		ltcheckbox.setSelected(b);;
	}
	public boolean setNode(){
		NodeCircle n=Preconditions.checkNotNull((NodeCircle)(topo.getSelectedItem()));
		n.setDomain(this.getDomain());
		String eid=getId();
		String nid=n.getId();
		if(!eid.equals(nid)){
			if(topo.isNodeIdUsed(eid)){
				JOptionPane.showMessageDialog(this, "Node ID used");
				return false;
			}
			else {
				if(!Strings.isNullOrEmpty(nid))topo.removeNodeId(nid);
			}
			
		}
    	n.setId(eid);
    	n.setDPID(this.getDPID());
    	n.setLabelTranslation((this.isLabelTranslation()));
    	topo.addNodeId(n.getId());
    	if(n.configComplete())n.setColor(Drawings.nodeConfigedColor);
    	else 
    		n.setColor(Drawings.defaultNodeColor);
    	topo.repaint();
    	return true;
	}
	public void getNode(){
		NodeCircle n=Preconditions.checkNotNull((NodeCircle)(topo.getSelectedItem()));
		setDomain(n.getDomain());
		setId(n.getId());
		setDPID(n.getDPID());
		setLabelTranslation(n.isLabelTranslation());
	}
}
