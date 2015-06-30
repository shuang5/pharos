package org.renci.pharos.gui;

import org.renci.pharos.lp.ComputeMode;

public final class CHOICES {
	private static ButtonOption currentChoice;
	private static Mode mode=Mode.NodeMode;
	private static ComputeMode computeMode=ComputeMode.ILP;
	private static Objectives objective=Objectives.LabelUsage;
	public CHOICES() {
		currentChoice=ButtonOption.Select;
		mode=Mode.NodeMode;
	}
	public static enum ButtonOption{
		Null,New,Open,Save,Select,Line,Node,Resize,Refresh,Request,Compute
	}
	
	ButtonOption getCurrentChoice(){
        return currentChoice;
	}
	void setCurrentChoice(ButtonOption c){
	 currentChoice=c;
	}
	void unsetCurrentChoice(ButtonOption c){
	 currentChoice=ButtonOption.Null;
	}
	Mode getMode(){
	 return mode;
	}
	void setMode(Mode m){
	 mode=m;
	}
	boolean isSelect(){
	 return currentChoice==ButtonOption.Select;
	}
	boolean isNode(){
	 return currentChoice==ButtonOption.Node;
	}
	boolean isLine(){
	 return currentChoice==ButtonOption.Line;
	}
	boolean isResize(){
	 return currentChoice==ButtonOption.Resize;
	}
	boolean isRequest(){
		 return currentChoice==ButtonOption.Request;
		}
	public static ComputeMode getComputeMode() {
		return computeMode;
	}
	public static void setComputeMode(ComputeMode computeMode) {
		CHOICES.computeMode = computeMode;
	}
	public static Objectives getObjective() {
		return objective;
	}
	public static void setObjective(Objectives objective) {
		CHOICES.objective = objective;
	}
}
