package org.renci.pharos.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.renci.pharos.flow.Rule;

import com.google.common.base.Preconditions;

public class RulesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopologyGraph topo;
	private JTextField actionField = new JTextField(30);
	private JTextField conditionField = new JTextField(30);
	private JTextArea rulesArea = new JTextArea();
    
    private JScrollPane scroll=new JScrollPane (rulesArea, 
  		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	RulesPanel(TopologyGraph t){
		//SpringLayout layout = new SpringLayout();
        //setLayout(new GridLayout(3, 2));	
		topo=t;
	    add(new JLabel("Condition:"));
	    add(conditionField);
	    add(new JLabel("Action:"));
	    add(actionField);
	    JButton addrule = new JButton("Add Rule");
	    addrule.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {                    	
                    	Rule r = new Rule();
                    	try{
                    		r.setCondition(conditionField.getText());
                    		r.setAction(actionField.getText());
                    		Preconditions.checkNotNull(((Line)(topo.getSelectedItem()))).addRule(r);                    		
                    		conditionField.setText(null);
                    		actionField.setText(null);
                    	}
                    	catch (Exception ex){
                    		ex.printStackTrace();
                    		JOptionPane.showMessageDialog(null, ex.getMessage());
                    	}
                    }
                });
	    add(addrule);
	    
	    JButton addruletable = new JButton("Add Rule Table");
	    addruletable.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) { 
                    	Rule r = new Rule();
                    	try{
                    		r.setCondition(conditionField.getText());
                    		r.setAction(actionField.getText());
                    		Preconditions.checkNotNull(((Line)(topo.getSelectedItem()))).addRuleTable(r);
                    		conditionField.setText(null);
                    		actionField.setText(null);
                    	}
                    	catch (Exception ex){
                    		JOptionPane.showMessageDialog(null, ex.getMessage());
                    	}
                    	 
                    }
                });
	    add(addruletable);
	    
	    JButton rules = new JButton("Show Rules");
	    rules.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {      
                    	if (!((Line)(topo.getSelectedItem())).rulesEmpty()){
                    		rulesArea.setText(((Line)(topo.getSelectedItem())).getRules());
                    	}
                    	int result = JOptionPane.showConfirmDialog(null, scroll, 
        	                    "Rules:", JOptionPane.OK_CANCEL_OPTION);
        	            if (result == JOptionPane.OK_OPTION) {
        	            }   
                    }
                });
	    add(rules);
	}
}
