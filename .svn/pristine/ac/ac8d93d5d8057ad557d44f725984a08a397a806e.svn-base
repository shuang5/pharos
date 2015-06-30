package org.renci.pharos.gui;

class NodeFactory{
	public static Circle createNode(Mode mode){
		if (mode==Mode.PortMode){
			return new PortCircle();
		}
		else if (mode==Mode.NodeMode){
			return new NodeCircle();
		}
		else return null;
	}
}