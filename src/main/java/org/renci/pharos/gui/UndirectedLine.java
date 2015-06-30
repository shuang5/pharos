package org.renci.pharos.gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public abstract class UndirectedLine extends Line{
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
        g2d.fillOval(x1-4, y1-4, 8, 8);
        g2d.fillOval(x2-4, y2-4, 8, 8);
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
		UndirectedCircle n1=(UndirectedCircle)getNode1();
		UndirectedCircle n2=(UndirectedCircle)getNode2();
		if(n1!=null)n1.removeLink(this);
		if(n2!=null)n2.removeLink(this);
		
	}
}