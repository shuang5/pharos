package org.renci.pharos.gui;

public class LineFactory{
	public static Line createLine(Mode mode, boolean isRequest){
		if (mode==Mode.PortMode && !isRequest){
			return new PortLine();
		}
		else if (mode==Mode.PortMode && isRequest){
			return new RequestPortLine();
		}
		else if (mode==Mode.NodeMode && !isRequest){
			return new NodeLine();
		}
		else if (mode==Mode.NodeMode && isRequest){
			return new RequestNodeLine();
		}
		else return null;
	}
}