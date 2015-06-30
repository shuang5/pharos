package org.renci.pharos.flow;

public class FlowPharosException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = null;
	public FlowPharosException(){
		super();
	}
	public FlowPharosException(String msg){
		super(msg);
		message=msg;
	}
	@Override
	public String toString(){
		return message;
	}
	@Override
	public String getMessage(){
		return message;
	}
}
