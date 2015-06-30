package org.renci.pharos.gui;

import com.tinkerpop.blueprints.Vertex;
public class PortCircle extends DirectedCircle implements BluePrintVertex{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vertex v;
	private PortProperties pp;
	{
		pp= new PortProperties();
	}
	public PortCircle(Vertex vv) {
    	v=vv;
		setShapeType(ShapeType.PortNode);
		 if (v.getProperty("Domain")!=null) 
			 pp.setDomain((String)v.getProperty("Domain"));
		 if (v.getProperty("DPID")!=null) 
			 pp.setDPID((String)v.getProperty("DPID"));
		 if (v.getProperty("Port")!=null) 
			 pp.setPort(Integer.parseInt((String)v.getProperty("Port")));
		 if (v.getProperty("Flowspace")!=null) {
			 String s=(String)(v.getProperty("Flowspace"));
			 if (!s.isEmpty())
				try {
					pp.setFlowspace((String)v.getProperty("Flowspace"));
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 if (v.getProperty("Flowvisor")!=null) {
			 String s=(String)(v.getProperty("Flowvisor"));
			 if (!s.isEmpty())pp.setFlowvisor((String)v.getProperty("Flowvisor"));
		 }
    	
	}
	public PortCircle() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public
	Vertex getBluePrintVertex(){
		return v;
	}
	public PortProperties portproperties(){
		return pp;
	}
	@Override
	public
	void setBluePrintVertex(Vertex vv){
		if(pp.getDomain()!=null)
				vv.setProperty("Domain",pp.getDomain());
		if(pp.getDPID()!=null)
			vv.setProperty("DPID",pp.getDPID());
		if(pp.getId()!=null)
			vv.setProperty("ID",pp.getId());
		if(pp.getPort()!=null)
			vv.setProperty("Port",pp.getPort());
		if(pp.getType()!=null)
			vv.setProperty("Type",pp.getType());
		if(pp.getFlowSpace()!=null)
			vv.setProperty("Flowspace",pp.getFlowSpace());
		if(pp.getFlowvisor()!=null)
			vv.setProperty("Flowvisor",pp.getFlowvisor());
		v=vv;
	}
	@Override
	boolean configComplete() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public
	void retrieveBluePrintVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}
	
	
}

