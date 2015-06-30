package org.renci.pharos.gui;

class NodeMenuFactory{
	public static CircleMenu createNodeMenu(TopologyGraph tg, Mode mode){
		if (mode==Mode.PortMode){
			return new PortCircleMenu(tg);
		}
		else if (mode==Mode.NodeMode){
			return new NodeCircleMenu(tg);
		}
		else return null;
	}
}