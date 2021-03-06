package org.renci.pharos.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.common.base.Preconditions;


public class PortCirclePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopologyGraph topo;
	private JTextField idField = new JTextField(5);
    private JTextField domainField = new JTextField(5);
    private JTextField dpidField = new JTextField(5);
    private JTextField portField = new JTextField(5);
    //private JTextField fvurlField = new JTextField(30);
    
    private String[] types={
    		"flowspace"
    };
    private JComboBox<?> combobox = new JComboBox<Object>(types);
    private JButton fs=new JButton("Flow Space");
    private FlowspacePanel fsp;
    private JButton fvurl = new JButton("Flowvisor");
    private FlowvisorPanel fvp;
	
	public PortCirclePanel(TopologyGraph t) {
		// TODO Auto-generated constructor stub
		FlowLayout layout = new FlowLayout();
        setLayout(layout);		
		add(new JLabel("ID:"));
        add(idField);
        //super.add(Box.createHorizontalStrut(15)); // a spacer
        add(new JLabel("Domain:"));
        add(domainField);
        add(new JLabel("DPID:"));
        add(dpidField);
        add(new JLabel("Port:"));
        add(portField);
        add(new JLabel("Flowvisor:"));
        //add(fvurlField);
        combobox.setSelectedIndex(-1);
        //add(new JLabel("Type:"));
        //add(combobox);
        topo=t;
        fsp=new PortFsPanel(Preconditions.checkNotNull((Circle)t.getSelectedItem()));
        fs.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {                    	
                    	int result = JOptionPane.showConfirmDialog(null, fsp, 
        	                    "Flowspace Panel", JOptionPane.OK_CANCEL_OPTION);
        	            if (result == JOptionPane.OK_OPTION) {
        	            }   
                    }
                });
        
        add(fs);
        fvp=new FlowvisorPanel();
        fvurl.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {  
                    	PortCircle pn=Preconditions.checkNotNull((PortCircle)(topo.getSelectedItem()));
                    	if (pn.portproperties().getFlowvisor()!=null)
                    		fvp.setUrl(pn.portproperties().getFlowvisor());
                    	else fvp.setUrl(null);
                    	int result = JOptionPane.showConfirmDialog(null, fvp, 
        	                    "Flowvisor Panel", JOptionPane.OK_CANCEL_OPTION);
        	            if (result == JOptionPane.OK_OPTION) {
        	            	pn.portproperties().setFlowvisor(fvp.getUrl());
        	            }   
                    }
                });
        add(fvurl);
        
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
	public String getPort(){
		return portField.getText();
	}
	public String getType(){
		return combobox.getSelectedItem().toString();
	}
	
	public void setId(String d){
		idField.setText(d);
	}
	public void setDomain(String d){
		domainField.setText(d);
	}
	public void setDPID(String d){
		dpidField.setText(d);
	}
	public void setPort(String d){
		portField.setText(d);
	}
	public void setType(String d){
		combobox.setSelectedItem(d);
	}
	
	public boolean isfvurl(String text){
		try{
			URL url = new URL(text);
			url.getPath();
		}
		catch (MalformedURLException e) {
			//JOptionPane.showMessageDialog(null, "Invalid url", "Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;

		}
}
