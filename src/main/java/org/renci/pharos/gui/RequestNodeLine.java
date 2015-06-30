package org.renci.pharos.gui;

import com.google.common.base.Strings;
import com.tinkerpop.blueprints.Edge;

class RequestNodeLine extends UndirectedCurvedDottedLine{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;
	private String ID;
	private int leftPort=-1;
	private int rightPort=-1;
	private String request1;
	private String request2;
	public RequestNodeLine(){
	}
	public RequestNodeLine(Edge e) {
   	 	setShapeType(ShapeType.NodeLine);
   	 	retrieveBluePrintEdge(e);
	}
	@Override
	public void setBluePrintEdge(Edge e, boolean reverse) {
		e.setProperty("ID", getId());
		e.setProperty("Request1", getRequest1());
		e.setProperty("Request2", getRequest2());
	}
	
	public void setLabel(String d){
		if(!Strings.isNullOrEmpty(d))label=d;
	}
	public void setId(String d){
		if(!Strings.isNullOrEmpty(d))ID=d;
	}
	public void setPort1(int d){
		((NodeCircle)getNode1()).associateLine2Port(this,d);
		leftPort=d;
	}
	public void setPort2(int d){
		((NodeCircle)getNode2()).associateLine2Port(this,d);
		rightPort=d;
	}
	public String getLabel(){
		return label;
	}
	public String getId(){
		return ID;
	}
	
	public int getLeftPort(){
		return leftPort;
	}
	public int getRightPort(){
		return rightPort;
	}
	public String getRequest1(){
		return request1;
	}
	public String getRequest2(){
		return request2;
	}
	public void setRequest1(String s){
		if(!Strings.isNullOrEmpty(s))request1=s;
	}
	public void setRequest2(String s){
		if(!Strings.isNullOrEmpty(s))request2=s;
	}
	@Override
	boolean configComplete() {
		if(Strings.isNullOrEmpty(this.getId()))return false;
		else if(Strings.isNullOrEmpty(this.getRequest1()))return false;
		else if(Strings.isNullOrEmpty(this.getRequest2()))return false;
		return true;
	}
	@Override
	public
	void retrieveBluePrintEdge(Edge e) {
		setId((String)e.getProperty("ID"));
		setRequest1((String)e.getProperty("Request1"));
		setRequest2((String)e.getProperty("Request2"));
		
	}
}