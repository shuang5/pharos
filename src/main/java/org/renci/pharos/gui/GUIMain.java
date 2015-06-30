package org.renci.pharos.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.renci.pharos.gui.CHOICES.ButtonOption;
import org.renci.pharos.lp.ComputeMode;
import org.renci.pharos.lp.LpProblem;
import org.renci.pharos.lp.MCFlowProblem;
import org.renci.pharos.lp.OptimizationResult;
import org.renci.pharos.lp.SSSDFlowProblem;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.tinkerpop.blueprints.Graph;

public class GUIMain extends JFrame {

	private static final long serialVersionUID = 1L;
	static CircleMenu nodePopup;
	static LineMenu edgePopup1;
	static LineMenu edgePopup2;
	static CHOICES cho;
	static TopologyGraph topo;

	private static ButtonPan buttonPan;
	private static JToolBar buttonPanel;
	static JLabel statusBar;
	private static DrawPanel drawingArea;
	private static int width = 1000, height = 800;

	@SuppressWarnings("unused")
	private static Color color = Color.blue;
	//int R, G, B;
	private static float stroke = 2.0f;
	private void initMenuBar(){
		GUIMenuBar mb=new GUIMenuBar();
		GUIMenu pharosMenu=new GUIMenu("Pharos");
		pharosMenu.add(new GUIPrefMenuItem())
				  .add(new GUIExitMenuItem());
		
		GUIMenu fileMenu=new GUIMenu("File");
		fileMenu.add(new GUINewMenuItem(this))
			.add(new GUISaveProviderMenuItem())
			.add(new GUISaveRequestMenuItem())
			.add(new GUILoadMenuItem())
			.add(new GUIExitMenuItem());
		
		GUIMenu helpMenu = new GUIMenu("Help");
		helpMenu.add(new GUIAboutMenuItem());
		
		GUIMenu computeMenu = new GUIMenu("Compute");
		GUILongestFirstMenuItem hm=new GUILongestFirstMenuItem();
		GUIShortestFirstMenuItem sm=new GUIShortestFirstMenuItem();
		GUIRandomMenuItem rm=new GUIRandomMenuItem();
		GUILpRelaxMenuItem irm=new GUILpRelaxMenuItem();
		GUIIlpMenuItem im=new GUIIlpMenuItem();
		im.getButton().setSelected(true);
		ButtonGroup group=new ButtonGroup();		
		group.add(hm.getButton());
		group.add(sm.getButton());
		group.add(rm.getButton());
		group.add(im.getButton());
		group.add(irm.getButton());
		computeMenu.add(hm).add(sm).add(rm).add(im).add(irm);
		
		GUIMenu objectiveMenu = new GUIMenu("Objective");
		GUILabelUsageMenuItem lu=new GUILabelUsageMenuItem();
		GUIPortUsageMenuItem pu=new GUIPortUsageMenuItem();
		GUILoadBalancingMenuItem bl=new GUILoadBalancingMenuItem();
		lu.getButton().setSelected(true);
		ButtonGroup objgroup=new ButtonGroup();	
		objgroup.add(lu.getButton());
		objgroup.add(pu.getButton());
		objgroup.add(bl.getButton());
		objectiveMenu.add(lu).add(pu).add(bl);
		
		mb.add(pharosMenu).add(fileMenu).add(computeMenu).add(objectiveMenu).add(helpMenu);
		super.setJMenuBar(mb.bar);
	}
	private void initPanel(){
		drawingArea = new DrawPanel(this);
		buttonPan = new ButtonPan(this, cho);
		buttonPanel = buttonPan.getButtonPanel();
		Container c = getContentPane();

		c.add(drawingArea, BorderLayout.CENTER);
		statusBar = new JLabel();
		c.add(statusBar, BorderLayout.SOUTH);
		statusBar.setText("     Welcome To The Topology GUI!!!   ");
		c.add(buttonPanel, BorderLayout.NORTH);
		setSize(width, height);
		setVisible(true);
	}
	private void initPopups(){
		nodePopup = NodeMenuFactory.createNodeMenu(topo, cho.getMode());
		edgePopup1 = LineMenuFactory.createEdgeMenu(topo, cho.getMode(),
				State.Provider);
		edgePopup2 = LineMenuFactory.createEdgeMenu(topo, cho.getMode(),
				State.Request);
	}
	public GUIMain() {
		super("Topology Generation GUI");
		cho = new CHOICES();
		topo = TopologyGraphFactory.createTopologyGraph(this, cho.getMode());	
		
		initMenuBar();
		
		initPanel();
		
		initPopups();
	}

	final LinkedList<Event> pressedEvents;
	{
		//order of events is important
		pressedEvents=Lists.newLinkedList();
		pressedEvents.add(new PopupTriggeredEvent());
		pressedEvents.add(new PopupVisibleEvent());
		pressedEvents.add(new SelectModeEvent(this));
		pressedEvents.add(new ResizeModeEvent(this));
		pressedEvents.add(new PortLineModeEvent(this));
		pressedEvents.add(new NodeLineModeEvent(this));
		pressedEvents.add(new NoOverlappingLineModeEvent(this));
		pressedEvents.add(new NodeModeEvent(this));
		pressedEvents.add(new RequestModeEvent(this));
	}
	final LinkedList<Event> releasedEvents;
	{
		//order of events is important
		releasedEvents=Lists.newLinkedList();
		releasedEvents.add(new PopupTriggeredEvent());
		releasedEvents.add(new RightMouseEvent());
		releasedEvents.add(new PopupVisibleEvent());
		releasedEvents.add(new SelectModeEvent(this));
		releasedEvents.add(new ResizeModeEvent(this));
		releasedEvents.add(new PortLineModeEvent(this));
		releasedEvents.add(new NodeLineModeEvent(this));
		releasedEvents.add(new NoOverlappingLineModeEvent(this));
		releasedEvents.add(new NodeModeEvent(this));
		releasedEvents.add(new RequestModeEvent(this));
	}
	final LinkedList<Event> draggedEvents;
	{
		//order of events is important
		draggedEvents=Lists.newLinkedList();
		draggedEvents.add(new PopupTriggeredEvent());
		draggedEvents.add(new SelectModeEvent(this));
		draggedEvents.add(new ResizeModeEvent(this));
		draggedEvents.add(new RequestModeEvent(this));
		draggedEvents.add(new CatchAllEvent(this));
	}
	void evaluateEvents(MouseEvent e,LinkedList<Event> events,MouseEventType m){
		for(Event ev:events){
			if(ev.test(e)){
				ev.act(m);
				break;
			}			
		}
	}
	void createNewItem(Drawings d) {
		d=Preconditions.checkNotNull(d);
		drawingArea.setCursor(Cursor
				.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		if (cho.isNode()) {
			if (cho.getMode() == Mode.NodeMode)
				((Circle) d).setShapeType(ShapeType.NodeNode);
			else if (cho.getMode() == Mode.PortMode)
				((Circle) d).setShapeType(ShapeType.PortNode);
			((Circle) d).setColor(Drawings.defaultNodeColor);
			((Circle) d).stroke = stroke;
			topo.setCurrentItem(d);
			topo.addItem(d);
		}
		else if (cho.isLine()) {
			if (cho.getMode() == Mode.NodeMode ){
				((Line) d).setShapeType(ShapeType.NodeLine);
				((Line) d).setColor(Drawings.defaultLineColor);
			}
			else if (cho.getMode() == Mode.PortMode){
				((Line) d).setShapeType(ShapeType.PortLine);
				((Line) d).setColor(Drawings.defaultLineColor);
			}
			((Line) d).stroke = stroke;
			topo.setCurrentItem(d);
			topo.addItem(d);
		} 
		else if(cho.isRequest()){
			if (cho.getMode() == Mode.NodeMode){
				((Line) d).setShapeType(ShapeType.RequestNodeLine);
				((Line) d).setColor(Drawings.defaultRequestLineColor);
			}
			((Line) d).stroke = stroke;
			topo.setCurrentItem(d);
			topo.addItem(d);
		}

	}
	static void saveRequest(){
		logger.log(Level.INFO, "choose file");
		Frame f = new Frame();
		FileDialog fd1 = new FileDialog(f, "Specify a file to save the request", FileDialog.SAVE);
		fd1.setVisible(true);

		String fDirectory = fd1.getDirectory();
		String fFile = fd1.getFile();

		f.dispose();
			if(fFile!=null && fDirectory!=null ){
			File fileName = new File (fDirectory+
					 System.getProperty("file.separator") + fFile);
			
			logger.log(Level.INFO, "save request");
			
			fileName.canWrite();
			if (fileName == null || fileName.getName().equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid File Name",
						"Invalid File Name", JOptionPane.ERROR_MESSAGE);
			} else{
				logger.log(Level.INFO, "saveRequest(fileName)");
				topo.saveRequestToFile(fileName);
			}
		}	
	}
	
	static void saveFile() {
		Frame f = new Frame();
		FileDialog fd1 = new FileDialog(f, "Specify a file to save the provider topology", FileDialog.SAVE);
		fd1.setVisible(true);

		String fFile = fd1.getFile();
		if(fFile==null)return;
		String fDirectory = fd1.getDirectory();

		f.dispose();
		File fileName = new File (fDirectory+
				 System.getProperty("file.separator") + fFile);
		
		if (fileName == null || fileName.getName().equals("")) {
			JOptionPane.showMessageDialog(null, "Invalid File Name",
					"Invalid File Name", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				topo.saveProviderTofile(fileName);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Save File Exception", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	static void loadFile() {
		Frame f = new Frame();
		FileDialog fd1 = new FileDialog(f, "Specify a file to load", FileDialog.LOAD);
		fd1.setVisible(true);

		String fDirectory = fd1.getDirectory();
		String fFile = fd1.getFile();
		if(fFile==null || fFile.isEmpty())return;
		f.dispose();
		File fileName = new File (fDirectory+
				 System.getProperty("file.separator") + fFile);
		
		if (fileName == null || fileName.getName().equals("") || !fileName.canRead()) {
			JOptionPane.showMessageDialog(null, "Invalid File Name",
					"Invalid File Name", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				topo.openFromFile(fileName);
			} catch (EOFException endofFileException) {
				JOptionPane.showMessageDialog(null, "no more record in file",
						"class not found", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException classNotFoundException) {
				JOptionPane.showMessageDialog(null, "Unable to Create Object",
						"end of file", JOptionPane.ERROR_MESSAGE);
			} catch (IOException ioException) {
				JOptionPane.showMessageDialog(null,
						"error during read from file", "read Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),
						"error during read from file",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void newFile() {
		cho.setCurrentChoice(ButtonOption.New);
		color = Color.black;
		stroke = 1.0f;
		topo.newTopo();
		repaint();
	}
	static ResultFrame rt=new ResultFrame();
	{
        rt.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	static void solve(ComputeMode mode, Objectives objective){		
		Graph p=((NodeTopologyGraph)topo).saveProviderToGraph();
		Graph r=((NodeTopologyGraph)topo).saveRequestToGraph();
		
		MCFlowProblem problem=new MCFlowProblem(new SSSDFlowProblem(new LpProblem(p,r)));
		final OptimizationResult or = problem.compute(mode,objective,p);
		
			//logger.log(Level.INFO,res.toString()+"\nObjective:"+String.valueOf(result.getObjective()));
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            UIManager.put("swing.boldMetal", Boolean.FALSE);	                
	            rt.setVisible(true);
	            rt.setResult(or);
	            if(or!=null && or.getObjective()!=null){            	
	            	rt.setTextField(or.getObjective());	                
	            	rt.setTextArea(StringUtils.join(or.getNzValues(), "\n"));
	            }
	            else {
	            	rt.setTextField("no feasible soltuion found!");	
	            	rt.setTextArea("");
	            }
	        }
        });
	}
	
	private final static Logger logger = Logger.getLogger(GUIMain.class.getName());
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;
	public static void init()throws SecurityException, IOException{
		 Logger l = Logger.getLogger(GUIMain.class.getName());
		 l.setLevel(Level.FINE);
		 fileTxt = new FileHandler("./data/logging.txt");
		 formatterTxt = new SimpleFormatter();
		 fileTxt.setFormatter(formatterTxt);
		 logger.addHandler(fileTxt);
	}
	
	public static void main(String args[]) throws SecurityException, IOException {
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		  		try {
			    	init();
		  			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		  		} 
		  		catch (Exception e) {e.printStackTrace();}
		  		GUIMain newPad = new GUIMain();
		  		newPad.addWindowListener(new WindowAdapter() {
		  			@Override
		  			public void windowClosing(WindowEvent e) {
		  				System.exit(0);
		  			}
		  		});
		      }
		    });
		
	}
}
