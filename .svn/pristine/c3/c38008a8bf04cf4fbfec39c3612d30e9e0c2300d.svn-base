package org.renci.pharos.gui;

abstract class DirectedCircle extends Circle {
	private static final long serialVersionUID = 1L;
	@Override
	void repositionAttached(){
		for (Drawings d: inLinks()){
			try{
    			d.x1=centerX();
    			d.y1=centerY();
    			
    		}
			catch (Exception ex){
				assert false;
			}
		}         
		for (Drawings d: outLinks()){
			try{
    			d.x2=centerX();
    			d.y2=centerY();
    		}
			catch (Exception ex){
				assert false;
			}
		}         
	}
	@Override
	void remove(){
		removeAllLinks();
	}
}