package org.renci.pharos.gui;

import com.google.common.base.Preconditions;

public abstract class UndirectedCircle extends Circle {
	private static final long serialVersionUID = 1L;
	void addLink(Line d){
	    addLinkedIn(Preconditions.checkNotNull(d));	    
	    addLinkedOut(d);
	}
	void removeLink(Line d){
    	removeLinkedIn(Preconditions.checkNotNull(d));
    	removeLinkedOut(d);
    }
	int getNumberOfLinks(){
    	return getNumberOfInLinks();
    }
	void addLinkReq(Line d){
	    addLinkedInReq(Preconditions.checkNotNull(d));
	    addLinkedOutReq(d);
	}
	void removeLinkReq(Line d){
    	removeLinkedInReq(Preconditions.checkNotNull(d));
    	removeLinkedOutReq(d);
    }
	int getNumberOfLinksReq(){
    	return getNumberOfInLinksReq();
    }
	@Override
	void remove(){
		removeAllLinks();
	}
	
}