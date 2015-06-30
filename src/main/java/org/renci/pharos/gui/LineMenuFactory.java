package org.renci.pharos.gui;

class LineMenuFactory{
	public static LineMenu createEdgeMenu(TopologyGraph tg, Mode mode, State state){
		if (mode==Mode.PortMode){
			return new PortLineMenu(tg);
		}
		else if (mode==Mode.NodeMode && state==State.Provider){
			return new NodeLineMenu(tg);
		}
		else if (mode==Mode.NodeMode && state==State.Request){
			return new RequestNodeLineMenu(tg);
		}
		else return null;
	}
}