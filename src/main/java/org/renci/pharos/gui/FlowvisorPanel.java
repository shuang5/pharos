package org.renci.pharos.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

public class FlowvisorPanel extends JPanel {
	private String url;
	private int port;
	public FlowvisorPanel() {
		add(new JLabel("https://"));
	    add(fvField);
	    add(new JLabel("Port:"));
	    add(portField);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fvField = new JTextField(30);
	private JTextField portField = new JTextField(5);
	
	public String getUrl(){
		url=fvField.getText();
		port=Integer.parseInt(portField.getText());
		return "https://" + url + ":" + port;
	}
	public void setUrl(String s){
		if (!StringUtils.isEmpty(s) && !StringUtils.isBlank(s)){
			try{
				String delims="[/:]+";
				String[] tokens = s.split(delims);
				fvField.setText(tokens[1]);
				portField.setText(tokens[2]);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}
}
