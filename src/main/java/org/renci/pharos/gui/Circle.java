package org.renci.pharos.gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;
public abstract class Circle extends Drawings implements BluePrintVertex
{
    
    public Circle(){
    	
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Set<Line> linkedToIn=new HashSet<Line>();
	private Set<Line> linkedToOut=new HashSet<Line>();
	private Set<Line> linkedToInReq=new HashSet<Line>();
	private Set<Line> linkedToOutReq=new HashSet<Line>();
	public Set<Line> inLinks(){
		return linkedToIn;
	}
	public Set<Line> outLinks(){
		return linkedToOut;
	}
	public Set<Line> inReqLinks(){
		return linkedToInReq;
	}
	public Set<Line> outReqLinks(){
		return linkedToOutReq;
	}
    void addLinkedIn(Line d){
    	linkedToIn.add(Preconditions.checkNotNull(d));
    }
    void removeLinkedIn(Line d){
    	linkedToIn.remove(Preconditions.checkNotNull(d));
    }
    void addLinkedOut(Line d){
    	linkedToOut.add(Preconditions.checkNotNull(d));
    }
    void removeLinkedOut(Line d){
    	linkedToOut.remove(Preconditions.checkNotNull(d));
    }
    void addLinkedInReq(Line d){
    	linkedToInReq.add(Preconditions.checkNotNull(d));
    }
    void removeLinkedInReq(Line d){
    	linkedToInReq.remove(Preconditions.checkNotNull(d));
    }
    void addLinkedOutReq(Line d){
    	linkedToOutReq.add(Preconditions.checkNotNull(d));
    }
    void removeLinkedOutReq(Line d){
    	linkedToOutReq.remove(Preconditions.checkNotNull(d));
    }
    void removeAllLinks(){
    	linkedToIn.clear();
    	linkedToOut.clear();
    	linkedToInReq.clear();
    	linkedToOutReq.clear();
    }
    int getNumberOfInLinks(){
    	if(linkedToIn.isEmpty())return 0;
    	else 
    		return linkedToIn.size();
    }
    int getNumberOfOutLinks(){
    	if(linkedToOut.isEmpty())return 0;
    	else 
    		return linkedToOut.size();
    }
    int getNumberOfInLinksReq(){
    	if(linkedToInReq.isEmpty())return 0;
    	else 
    		return linkedToInReq.size();
    }
    int getNumberOfOutLinksReq(){
    	if(linkedToOutReq.isEmpty())return 0;
    	else 
    		return linkedToOutReq.size();
    }
	@Override
	void draw(Graphics2D g2d) {
		Ellipse2D.Double circle = new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2),
                Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)),
                Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
		//Ellipse2D.Double circle = new Ellipse2D.Double(x1,y1,x2,x2);
        g2d.setPaint(getColor());
        g2d.setStroke(new BasicStroke(stroke));
        shape=circle;
        g2d.draw(circle);
        g2d.setColor(getColor());
        g2d.fill(circle);
                
		//ImageIcon icon = new ImageIcon("figs/circle.png");
	    //g2d.drawImage(icon.getImage(), x2, y2, null);
	    
	    
    }
	@Override
	boolean contains(int x,int y){
		if (x>=(Math.min(x1, x2)-1) && x<= (Math.min(x1, x2)+Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2))+1) &&
				y>=(Math.min(y1, y2)-1) && y<=(Math.min(y1, y2)+Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2))+1))
				return true;
		else return false;
		//return getShape().contains(x,y);
	}
	@Override
	int centerX(){
		return Math.min(x1, x2)+Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2))/2;
	}
	@Override
	int centerY(){
		return Math.min(y1, y2)+Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2))/2;
	}
	abstract String getId();
	
}