package org.renci.pharos.gui;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.renci.pharos.flow.FlowPharosException;
import org.renci.pharos.flow.FlowSpace;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.tinkerpop.blueprints.Vertex;

public final class NodeCircle extends UndirectedCircle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Domain;
	private String ID;
	private String DPID;
	private String Type;
	private Vertex v;
	private String FV;
	private boolean labelTranslation=false;
	
	private Set<Line> originalIn=Sets.newHashSet();
	private Set<Line> originalOut=Sets.newHashSet();
	private Set<Line> originalReqIn=Sets.newHashSet();
	private Set<Line> originalReqOut=Sets.newHashSet();
	@Override
	public Set<Line> inLinks(){
		return originalIn;
	}
	@Override
	public Set<Line> outLinks(){
		return originalOut;
	}
	@Override
	public Set<Line> inReqLinks(){
		return originalReqIn;
	}
	@Override
	public Set<Line> outReqLinks(){
		return originalReqOut;
	}
	//FlowSpace flowspace=new FlowSpace();
	private ArrayList<NodePortProperties> ports=new ArrayList<NodePortProperties>();
	private ArrayList<Line> lines=new ArrayList<Line>();
	public NodeCircle(){
	}
	public NodeCircle(Vertex vv) {
    	v=Preconditions.checkNotNull(vv);;
		setShapeType(ShapeType.NodeNode);
		retrieveBluePrintVertex(vv);
    	
	}
	@Override
	public void addLink(Line l){
		l=Preconditions.checkNotNull(l);
		if(l.getShapeType()==ShapeType.NodeLine){
			lines.add(l);
			NodePortProperties pp=new NodePortProperties();
			pp.setLineAssociation(l);
			ports.add(pp);
			super.addLink(l);
		}
		else if(l.getShapeType()==ShapeType.RequestNodeLine){
			super.addLinkReq(l);
		}		
	}
	public void addLink(Line l, int port){
		l=Preconditions.checkNotNull(l);
		if(l.getShapeType()==ShapeType.NodeLine){
			lines.add(l);
			ports.get(port).setLineAssociation(l);
			super.addLinkedIn(l);
		}	
	}
	public void addLink(int port, Line l){
		l=Preconditions.checkNotNull(l);
		if(l.getShapeType()==ShapeType.NodeLine){
			lines.add(l);
			ports.get(port).setLineAssociation(l);
			super.addLinkedOut(l);
		}	
	}
	public void addLinkIn(Line l){
		l=Preconditions.checkNotNull(l);
		if(l.getShapeType()==ShapeType.NodeLine){
			originalIn.add(l);
		}
		else if(l.getShapeType()==ShapeType.RequestNodeLine){
			originalReqIn.add(l);
		}	

		addLink(l);
	}
	public void addLinkOut(Line l){
		l=Preconditions.checkNotNull(l);
		if(l.getShapeType()==ShapeType.NodeLine){
			originalOut.add(l);
		}
		else if(l.getShapeType()==ShapeType.RequestNodeLine){
			originalReqOut.add(l);
		}	

		addLink(l);
	}
	@Override
	public void removeAllLinks(){
		lines.clear();
		ports.clear();
		originalIn.clear();
		originalOut.clear();
		originalReqIn.clear();
		originalReqOut.clear();
		super.removeAllLinks();
	}
	@Override
	public void removeLink(Line l){
		l=Preconditions.checkNotNull(l);
		if(lines.contains(l)){
			int i=lines.indexOf(l);
			lines.remove(i);
			ports.remove(i);
		}
		if(originalIn.contains(l))originalIn.remove(l);
		else if(originalOut.contains(l))originalOut.remove(l);
		else if(originalReqIn.contains(l))originalReqIn.remove(l);
		else if(originalReqOut.contains(l))originalReqOut.remove(l);
		
		if(l.getShapeType()==ShapeType.NodeLine){
			super.removeLink(l);
		}
		else if(l.getShapeType()==ShapeType.RequestNodeLine){
			super.removeLinkReq(l);
		}
	}
	@Override
	public Vertex getBluePrintVertex(){
		return v;
	}
	@Override
	public void setBluePrintVertex(Vertex vv){
		vv=Preconditions.checkNotNull(vv);
		if(getDomain()!=null)
				vv.setProperty("Domain",getDomain());
		if(getDPID()!=null)
			vv.setProperty("DPID",getDPID());
		if(getId()!=null)
			vv.setProperty("ID",getId());
		if(getType()!=null)
			vv.setProperty("Type",getType());
		if(getPorts()!=null)
			vv.setProperty("Ports",getPorts());
		if(isLabelTranslation())
			vv.setProperty("LabelTranslation","yes");
		else 
			vv.setProperty("LabelTranslation","no");
		v=vv;
	}
	@Override
	public void retrieveBluePrintVertex(Vertex vv){
		vv=Preconditions.checkNotNull(vv);
		setDomain((String) vv.getProperty("Domain"));
		setDPID((String) vv.getProperty("DPID"));
		setId((String) vv.getProperty("ID"));
		setType((String) vv.getProperty("Type"));
		if(((String)vv.getProperty("LabelTranslation")).equals("yes"))
			setLabelTranslation(true);
		else 
			setLabelTranslation(false);
		try {
			setPorts((String) vv.getProperty("Ports"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setDomain(String d){
		if(d!=null)Domain=d;
	}
	public void setId(String d){
		if(d!=null)ID=d;
	}
	public void setDPID(String d){
		if(d!=null)DPID=d;
	}
	
	public void setType(String d){
		if(d!=null)Type=d;
	}
	public void setPort(NodePortProperties p){
		p=Preconditions.checkNotNull(p);
		int id=Integer.parseInt(p.getId());
		try{
			ports.set(id, p);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setPorts(String p) throws Exception{
		if(StringUtils.isEmpty(p) || StringUtils.isBlank(p))return;
		String delims="[;]";
		String[] tokens=p.split(delims);
		for (String s: tokens){
			if(s==null || s.equals("null"))continue;
			NodePortProperties pp=new NodePortProperties(s);
			try{
				ports.add(pp);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setFlowvisor(String d){
		if(!Strings.isNullOrEmpty(d))FV=d;
	}
	public String getFlowvisor(){
		return FV;
	}
	public String getDomain(){
		return Domain;
	}
	@Override
	public String getId(){
		return ID;
	}
	
	public String getType(){
		return Type;
	}
	public String getDPID(){
		return DPID;
	}
	public NodePortProperties getPort(int id){
		return ports.get(id);
	}
	public String getPorts(){
		String s=new String();
		for(NodePortProperties p:ports){
			s+=p;
			s+=';';
		}
		return s;
	}
	public FlowSpace getFlowSpaceUnionOfAllPorts() throws FlowPharosException{
		FlowSpace fs=new FlowSpace();
		for(NodePortProperties p:ports){
			fs.merge(new FlowSpace(p.getFlowSpace()));
		}
		return fs;
	}
	public void associateLine2Port(Line l, int port){
		ports.get(port).setLineAssociation(l);
	}
	public boolean portAssociated2Line(int port, NodeLine edge) {
		if(((NodeLine)ports.get(port).getLineAssociation()).getId().equals(edge.getId()))return true;
		else
			return false;
	}
	public NodePortProperties portproperties(int portSelected) {
		return ports.get(portSelected);
	}
	public boolean isLabelTranslation() {
		return labelTranslation;
	}
	public void setLabelTranslation(boolean labelTranslation) {
		this.labelTranslation = labelTranslation;
	}
	@Override
	void repositionAttached(){
		for (Drawings d: inLinks()){
			d.x2=centerX();
			d.y2=centerY();
		}  
		for (Drawings d: outLinks()){
			d.x1=centerX();
			d.y1=centerY();
		}  
		for (Drawings d: inReqLinks()){
			d.x2=centerX();
			d.y2=centerY();
		}  
		for (Drawings d: outReqLinks()){
			d.x1=centerX();
			d.y1=centerY();
		}  
	}
	@Override
	public boolean configComplete() {
		if(Strings.isNullOrEmpty(this.getDomain()))return false;
		else if(Strings.isNullOrEmpty(this.getDPID()))return false;
		else if(Strings.isNullOrEmpty(this.getId()))return false;
		else {
			for(NodePortProperties p:ports){
				if(Strings.isNullOrEmpty(p.getFlowSpace()))return false;
			}
		}
		return true;
	}
}
