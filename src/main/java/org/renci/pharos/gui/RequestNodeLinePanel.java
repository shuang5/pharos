package org.renci.pharos.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;


public class RequestNodeLinePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopologyGraph topo;
	private JTextField idField = new JTextField(5);
	private JTextField RequestField1 = new JTextField(5);
	private JTextField RequestField2 = new JTextField(5);
	
    private String node1id,node2id;
    
    private RequestNodeLine edge;
	public RequestNodeLinePanel(TopologyGraph t) {
		topo=t;
		edge=Preconditions.checkNotNull((RequestNodeLine)topo.getSelectedItem());
        
        add(new JLabel("ID:"));
        if(edge.getId()!=null) setId(edge.getId());
        add(idField);   
        
        node1id=((NodeCircle)(edge.getNode1())).getId();
        if (StringUtils.isEmpty(node1id) || StringUtils.isBlank(node1id))
        	node1id="Unknown";
        add(new JLabel(node1id));
        if(edge.getRequest1()!=null){
        	String[] res=edge.getRequest1().split("[:]");
        	setRequest1(res[1]);
        }
        add(RequestField1); 
        
        node2id=((NodeCircle)(edge.getNode2())).getId();
        if (StringUtils.isEmpty(node2id) || StringUtils.isBlank(node2id))
        	node2id="Unknown";
        add(new JLabel(node2id));
        if(edge.getRequest2()!=null){
        	String[] res=edge.getRequest2().split("[:]");
        	setRequest2(res[1]);
        }
        add(RequestField2); 
        
        
	}
	
	public String getId(){
		return idField.getText();
	}
	
	public void setId(String d){
		if(!Strings.isNullOrEmpty(d))idField.setText(d);
	}
	
	public String getRequest1(){
		if(!RequestField1.getText().matches("\\d+"))
			throw new NumberFormatException();
		else 
			return node2id+":"+RequestField1.getText();
	}
	
	public void setRequest1(String d){
		if(!Strings.isNullOrEmpty(d))RequestField1.setText(d);
	}
	
	public String getRequest2(){
		if(!RequestField1.getText().matches("\\d+"))
			throw new NumberFormatException();
		else 
			return node1id+":"+RequestField2.getText();
	}
		
	public void setRequest2(String d){
		if(!Strings.isNullOrEmpty(d))RequestField2.setText(d);
	}
}

