package org.renci.pharos.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import org.renci.pharos.flow.FlowPharosException;
import org.renci.pharos.flow.Rule;
import org.renci.pharos.flow.Rules;


public abstract class Line extends Drawings implements BluePrintEdge
{
	private static final long serialVersionUID = 1L;
	protected boolean xSelected=false,ySelected=false;
	protected Rules rules=new Rules();
	//node attached to (x1,y1),
	//convention is for the line, it leaves node1 and enters node2
	private Circle node1; 
	//node attached to (x2,y2)
	private Circle node2; 
	boolean virtual=false;
	Line(){
		setColor(Color.black);
	}
	public void setVirtual(boolean v){
		virtual=v;
	}
	public boolean isVirtual(){
		return virtual;
	}
	public void setNode1(Circle n){
		
		node1=n;
	}
	public void setNode2(Circle n){
		node2=n;
	}
	public Circle getNode1(){
		return node1;
	}
	public Circle getNode2(){
		return node2;
	}
	public void switchDirection(){
		int xx1=this.x1;
		int yy1=this.y1;
		this.x1=this.x2;
		this.y1=this.y2;
		this.x2=xx1;
		this.y2=yy1;
	}

	public boolean isXSelected (){
		return xSelected;
	}
	public boolean isYSelected (){
		return ySelected;
	}
	@Override
	void repositionAttached(){
		
	}

	@Override
	void draw(Graphics2D g2d) {
        g2d.setPaint(getColor());
        g2d.setStroke(new BasicStroke(stroke,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        shape=line;
        g2d.draw(line);
        //g2d.fill(createArrowShape(new Point(x1,y1), new Point (x2,y2)));
    }
	
	@Override
	int centerX(){
		return (x1+x2)/2;
	}
	@Override
	int centerY(){
		return (y1+y2)/2;
	}
	public void setRules(String d){
		rules=new Rules(d);
	}
	public String getRules(){
		if(rules!=null)
			return rules.toString();
		else return null;
	}
	public void addRule(Rule r){
		try {
			rules.addRule(r);
		} catch (FlowPharosException e) {
			e.printStackTrace();
		}
	}
	public void addRuleTable(Rule r){
		rules.addRuleTable(r);	
	}
	public boolean rulesEmpty(){
		return rules==null;
	}
	
	
}