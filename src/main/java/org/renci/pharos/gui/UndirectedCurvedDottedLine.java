package org.renci.pharos.gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * @author shuang
 *
 */
public abstract class UndirectedCurvedDottedLine extends Line{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int controlx, controly;
	
	public UndirectedCurvedDottedLine(){
		controlx=x1;
		controly=y1;
	}
	public void setControlPoint (int x, int y){
		controlx=x;
		controly=y;
	}
	public int getControlX(){
		return controlx;
	}
	public int getControlY(){
		return controly;
	}
	@Override
	void draw(Graphics2D g2d) {
        g2d.setPaint(getColor());
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, 
        		BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        //Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        QuadCurve2D.Double curve = new QuadCurve2D.Double(x1,y1,controlx,controly,x2,y2);
        //by changing the control point along a line, we could creat a crescent shape 
        //which is used catch mouse focus event
        //QuadCurve2D.Double innerCurve=new QuadCurve2D.Double(x1,y1,controlx+6,controly+6,x2,y2);
        //QuadCurve2D.Double outerCurve=new QuadCurve2D.Double(x1,y1,controlx-6,controly-6,x2,y2);
        shape=curve;
        //g2d.setRenderingHint(
        //	    RenderingHints.KEY_ANTIALIASING,
        //	    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(curve);
        //g2d.setColor(Color.blue);
        //g2d.draw(innerCurve);

        //g2d.setColor(Color.red);
        //g2d.draw(outerCurve);
    }
	
	class Pair{
		public Pair(int xx1, int yy1, int xx2, int yy2) {
			x1=xx1;
			y1=yy1;
			x2=xx2;
			y2=yy2;
		}
		public int x1;
		public int y1;
		public int x2;
		public int y2;
	}
	@Override
	boolean contains(int x,int y){
		xSelected=false;
		ySelected=false;
		//the control point formula is: controlx=(x1*3+x2)/4, controly=(y2*3+y1)/4
        QuadCurve2D.Double innerCurve=new QuadCurve2D.Double(x1,y1,controlx+6,controly+6,x2,y2);
        QuadCurve2D.Double outerCurve=new QuadCurve2D.Double(x1,y1,controlx-6,controly-6,x2,y2);
        Point2D.Double p=new Point2D.Double(x,y);
        if(innerCurve.contains(p)){
        	if(!outerCurve.contains(p))return true;
        }
        else if(outerCurve.contains(p)){
        	return true;
        }
		return false;
		//return getShape().getBounds().contains(x,y);
	}
	@Override
	void remove(){
		UndirectedCircle n1=(UndirectedCircle)getNode1();
		UndirectedCircle n2=(UndirectedCircle)getNode2();
		if(n1!=null)n1.removeLinkReq(this);
		if(n2!=null)n2.removeLinkReq(this);
	}
}