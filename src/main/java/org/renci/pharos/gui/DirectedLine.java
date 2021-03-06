package org.renci.pharos.gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import com.google.common.base.Preconditions;

abstract class DirectedLine extends Line{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	void draw(Graphics2D g2d) {
        g2d.setPaint(getColor());
        g2d.setStroke(new BasicStroke(stroke,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        shape=line;
        g2d.draw(line);
        g2d.fill(createArrowShape(new Point(x1,y1), new Point (x2,y2)));
    }
	//node1 is the start node
	public void setStartNode(Circle n){
		setNode1(Preconditions.checkNotNull(n));
	}
	//node2 is the end node
	public void setEndNode(Circle n){
		setNode2(Preconditions.checkNotNull(n));
	}
	public DirectedCircle getStartNode(){
		return (DirectedCircle)getNode1();
	}
	public DirectedCircle getEndNode(){
		return (DirectedCircle)getNode2();
	}
	@Override
	boolean contains(int x,int y){
		xSelected=false;
		ySelected=false;
		if (x<Math.min(x1, x2) || x>Math.max(x1, x2) || y<Math.min(y1, y2) || y>Math.max(y1, y2))
			return false;
		double d=Math.abs((x2-x1)*(y1-y)-(x1-x)*(y2-y1))/Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
		if(d<3) {
			if(Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1))<3) xSelected=true;
			else if(Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2))<3) ySelected=true;
			return true;
		}
		else return false;
		//return getShape().getBounds().contains(x,y);
	}
	@Override
	void remove(){
		getStartNode().removeLinkedOut(this);
		getEndNode().removeLinkedIn(this);
	}
}