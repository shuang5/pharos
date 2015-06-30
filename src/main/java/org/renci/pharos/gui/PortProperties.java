package org.renci.pharos.gui;

import org.apache.commons.lang.StringUtils;
import org.renci.pharos.flow.FlowSpace;
import org.renci.pharos.flow.FlowUnit;

public final class PortProperties {
	private String Domain;
	private String ID;
	private String DPID;
	private String Type;
	private int Port=-1;
	private String FV;
	private PortProperties peer;
	private Line associatedLine=null;
	FlowSpace flowspace;
	//static Logger log=Logger.getLogger(PortProperties.class.getName());
	public void setDomain(String d){
		if(!StringUtils.isEmpty(d) && !StringUtils.isBlank(d))Domain=d;
	}
	public void setId(String d){
		if(!StringUtils.isEmpty(d) && !StringUtils.isBlank(d))ID=d;
	}
	public void setDPID(String d){
		if(!StringUtils.isEmpty(d) && !StringUtils.isBlank(d))DPID=d;
	}
	public void setPort(int d){
		Port=d;
	}
	public void setType(String d){
		if(!StringUtils.isEmpty(d) && !StringUtils.isBlank(d))Type=d;
	}
	public void setFlowspace(String fs) throws Exception{
		if(StringUtils.isEmpty(fs) || StringUtils.isBlank(fs))return;
		try{
			flowspace=new FlowSpace(fs);
		}
		catch(Exception ex){
			throw ex;
		}
	}
	public void setFlowvisor(String d){
		if(!StringUtils.isEmpty(d) && !StringUtils.isBlank(d))FV=d;
	}
	public void setLineAssociation(Line l){
		if(l!=null)associatedLine=l;
	}
	public String getFlowvisor(){
		return FV;
	}
	public String getDomain(){
		return Domain;
	}
	public String getId(){
		return ID;
	}
	public String getPort(){
		return Integer.toString(Port);
	}
	public String getType(){
		return Type;
	}
	public String getDPID(){
		return DPID;
	}
	public Line getLineAssociation(){
		return associatedLine;
	}
	public void setPeer(PortProperties p){
		peer=p;
	}
	public PortProperties getPeer(){
		return peer;
	}
	public String getFlowSpace(){
		return flowspace.toString();
	}
	public void addFlowSpace(FlowUnit fu){
		flowspace.add(fu);
	}
	public void removeFlowSpace(FlowUnit fu){
		flowspace.remove(fu);
	}
	@Override
	public String toString(){
		return Domain+','+ID+','+DPID+','+Type+','+getPort()+','+getFlowSpace();
	}
	public PortProperties(String s) {
		if(StringUtils.isEmpty(s) || StringUtils.isBlank(s) || s.equals("null"))
			return;
		String delims="[,]";
		String[] tokens=s.split(delims);
		setDomain(tokens[0]);
		setId(tokens[1]);
		setDPID(tokens[2]);
		setType(tokens[3]);
		setPort(Integer.parseInt(tokens[4]));
		try {
			setFlowspace(tokens[5]);
		}
		catch (Exception ex){
			//write to log
			//log.debug("flowspace parse error");
		}
	}
	
	protected PortProperties(){
		flowspace=new FlowSpace();
	}
}