package org.renci.pharos.gui;

import com.tinkerpop.blueprints.Vertex;

public interface BluePrintVertex {
	public Vertex getBluePrintVertex();
	public void setBluePrintVertex(Vertex v);
	public void retrieveBluePrintVertex(Vertex v);
}
