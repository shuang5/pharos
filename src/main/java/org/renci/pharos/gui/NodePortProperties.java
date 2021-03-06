package org.renci.pharos.gui;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.renci.pharos.flow.FlowSpace;
import org.renci.pharos.flow.FlowUnit;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class NodePortProperties {
	private String ID;
	private String Type;
	private int Port=-1;
	private NodePortProperties peer;
	private Line associatedLine=null;
	private int unitsRequested;
	FlowSpace flowspace;
	public void setUnitsRequested(int r){
		unitsRequested=r;
	}
	public int getUnitsRequested(){
		return unitsRequested;
	}
	public void setId(String d){
		if(!Strings.isNullOrEmpty(d))ID=d;
	}
	public void setPort(int d){
		Port=d;
	}
	public void setType(String d){
		if(!Strings.isNullOrEmpty(d))Type=d;
	}
	public void setFlowspace(String fs) throws Exception{
		if(fs==null)return;
		try{
			flowspace=new FlowSpace(fs);
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public void setLineAssociation(Line l){
		associatedLine=Preconditions.checkNotNull(l);
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
	public Line getLineAssociation(){
		return associatedLine;
	}
	public void setPeer(NodePortProperties ppr){
		peer=Preconditions.checkNotNull(ppr);
	}
	public NodePortProperties getPeer(){
		return peer;
	}
	public String getFlowSpace(){
		return flowspace.toString();
	}
	public void addFlowSpace(FlowUnit fu){
		flowspace.add(Preconditions.checkNotNull(fu));
	}
	public void removeFlowSpace(FlowUnit fu){
		flowspace.remove(Preconditions.checkNotNull(fu));
	}
	@Override
	public String toString(){
		String s=(flowspace==null)?"null":flowspace.toString();
		return ID+','+Type+','+getPort()+','+s;
	}
	public NodePortProperties(String s) {
		if(StringUtils.isEmpty(s) || StringUtils.isBlank(s) || s.equals("null"))
			return;
		String delims="[,]";		
		String[] tokens=s.split(delims);
		if(tokens[0].equals("null"))return;
		setId(tokens[0]);
		setType(tokens[1]);
		setPort(Integer.parseInt(tokens[2]));
		try {
			setFlowspace(tokens[3]);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public NodePortProperties(String s, int port) {
		if(StringUtils.isEmpty(s) || StringUtils.isBlank(s) || s.equals("null"))
			return;
		String outdelim="[;]";
		String[] outtokens=s.split(outdelim);		
		if(outtokens[port].equals("null")) return;
		String delims="[,]";
		String[] tokens=outtokens[port].split(delims);
		if(tokens[0].equals("null")) return;
		setId(tokens[0]);
		setType(tokens[1]);
		setPort(Integer.parseInt(tokens[2]));
		try {
			setFlowspace(tokens[3]);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	public static ArrayList<NodePortProperties> getNodePortProepertiesList(String s){
		if(StringUtils.isEmpty(s) || StringUtils.isBlank(s) || s.equals("null"))
			return null;
		ArrayList<NodePortProperties> arr=new ArrayList<NodePortProperties>();
		String delim="[;]";
		String[] outtokens=s.split(delim);	
		for(String ss:outtokens){
			if(ss.equals("null"))continue;
			arr.add(new NodePortProperties(ss));
		}
		return arr;
		
	}
	protected NodePortProperties(){
		flowspace=new FlowSpace();
	}
}
