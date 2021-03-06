package org.renci.pharos.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

abstract class Drawings implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1788928423201041136L;
	public static final Color defaultNodeColor=Color.blue;
	public static final Color defaultLineColor=Color.black;
	public static final Color defaultRequestLineColor=Color.gray;
	public static final Color nodeConfigedColor=new Color(0,128,128);
	public static final Color lineConfigedColor=new Color(128,128,0);
	public static final Color requestConfigedColor=new Color(128,0,0);
	int x1, y1, x2, y2; 
	private Color color = defaultNodeColor;      
    float stroke;      
    private ShapeType type;              
    protected Shape shape;
    
    Shape getShape(){
    	return shape;
    }
    public void setColorBrighter(){
    	setColor(getColor().brighter());
    }
    public void setColorDarker(){
    	setColor(getColor().darker());
    }
    abstract int centerX();
    abstract int centerY();
    abstract boolean contains(int x,int y);
    abstract void draw(Graphics2D g2d);
    abstract void repositionAttached();
    abstract void remove();
    abstract boolean configComplete();
	public static Shape createArrowShape(Point fromPt, Point toPt) {
		    Polygon arrowPolygon = new Polygon();
		    arrowPolygon.addPoint( 0,5);
		    arrowPolygon.addPoint( -5, -5);
		    arrowPolygon.addPoint( 5,-5);


		    double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

		    AffineTransform transform = new AffineTransform();
		   
		    transform.translate(midpoint(fromPt,toPt).x, midpoint(fromPt,toPt).y);
		    transform.rotate((rotate-Math.PI/2d)); 

		    return transform.createTransformedShape(arrowPolygon);
		}

		private static Point midpoint(Point p1, Point p2) {
		    return new Point((int)((p1.x + p2.x)/2.0), 
		                     (int)((p1.y + p2.y)/2.0));
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
		public ShapeType getShapeType() {
			return type;
		}
		public void setShapeType(ShapeType type) {
			this.type = type;
		}

}