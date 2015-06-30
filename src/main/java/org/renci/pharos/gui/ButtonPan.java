package org.renci.pharos.gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.renci.pharos.gui.CHOICES.ButtonOption;

import com.google.common.collect.Maps;

class ButtonPan {
	private JToolBar buttonPanel;
	private CHOICES cho;
	private GUIMain guimain;

	public JToolBar getButtonPanel() {
		return buttonPanel;
	}
	static HashMap<ButtonOption, Icon> iconOnMap=Maps.newHashMap();
	{
		iconOnMap.put(ButtonOption.New, new ImageIcon("figs/NewOn.png"));
		iconOnMap.put(ButtonOption.Open, new ImageIcon("figs/OpenOn.png"));
		iconOnMap.put(ButtonOption.Save, new ImageIcon("figs/SaveOn.png"));
		iconOnMap.put(ButtonOption.Select, new ImageIcon("figs/SelectOn.png"));
		iconOnMap.put(ButtonOption.Line, new ImageIcon("figs/LineOn.png"));
		iconOnMap.put(ButtonOption.Node, new ImageIcon("figs/NodeOn.png"));
		iconOnMap.put(ButtonOption.Resize, new ImageIcon("figs/ResizeOn.png"));
		iconOnMap.put(ButtonOption.Refresh, new ImageIcon("figs/RefreshOn.png"));
		iconOnMap.put(ButtonOption.Request, new ImageIcon("figs/RequestOn.png"));
		iconOnMap.put(ButtonOption.Compute, new ImageIcon("figs/ComputeOn.png"));
	}
	static HashMap<ButtonOption, Icon> iconOffMap=Maps.newHashMap();
	{
		iconOffMap.put(ButtonOption.New, new ImageIcon("figs/NewOff.png"));
		iconOffMap.put(ButtonOption.Open, new ImageIcon("figs/OpenOff.png"));
		iconOffMap.put(ButtonOption.Save, new ImageIcon("figs/SaveOff.png"));
		iconOffMap.put(ButtonOption.Select, new ImageIcon("figs/SelectOff.png"));
		iconOffMap.put(ButtonOption.Line, new ImageIcon("figs/LineOff.png"));
		iconOffMap.put(ButtonOption.Node, new ImageIcon("figs/NodeOff.png"));
		iconOffMap.put(ButtonOption.Resize, new ImageIcon("figs/ResizeOff.png"));
		iconOffMap.put(ButtonOption.Refresh, new ImageIcon("figs/RefreshOff.png"));
		iconOffMap.put(ButtonOption.Request, new ImageIcon("figs/RequestOff.png"));
		iconOffMap.put(ButtonOption.Compute, new ImageIcon("figs/ComputeOff.png"));
	}
	abstract class Button extends JButton{
		private static final long serialVersionUID = 1L;
		private boolean state;
		@Override
		public abstract String getName();
		public abstract String getTipText();
		public void SetIconOn(){
			buttonMap.get(ButtonOption.Select).setIcon(iconOffMap.get(ButtonOption.Select));
			buttonMap.get(ButtonOption.Line).setIcon(iconOffMap.get(ButtonOption.Line));
			buttonMap.get(ButtonOption.Node).setIcon(iconOffMap.get(ButtonOption.Node));
			buttonMap.get(ButtonOption.Resize).setIcon(iconOffMap.get(ButtonOption.Resize));
			buttonMap.get(ButtonOption.Request).setIcon(iconOffMap.get(ButtonOption.Request));
		}
		public boolean isOn() {
			return state;
		}
		public void setOn(boolean state) {
			this.state = state;
		}
		public Button(String s, Icon i){
			super(s,i);
		}
	}
	
	class NewButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.New;
		static final String name="New";
		static final String tipText= "Draw a new topology";
		public NewButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guimain.newFile();
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {
			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.New);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.New));
			} else {
				cho.setCurrentChoice(ButtonOption.New);
				setOn(true);
				setIcon(iconOnMap.get(ButtonOption.New));
			}
			
		}
	}
	
	class OpenButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Open;
		static final String name="Open";
		static final String tipText= "Open a saved topology";
		public OpenButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIMain.loadFile();
			}
		});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Open);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Open));
			} else {
				cho.setCurrentChoice(ButtonOption.Open);
				setOn(true);
				setIcon(iconOnMap.get(ButtonOption.Open));
			}
		}
		
	}
	class SaveButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Save;
		static final String name="Save";
		static final String tipText="Save current drawing";
		public SaveButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
							GUIMain.topo.saveProviderTofile(fileName);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),
									"Save File Exception", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Save);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Save));
			} else {
				cho.setCurrentChoice(ButtonOption.Save);
				setOn(true);
				setIcon(iconOnMap.get(ButtonOption.Save));
			}
			
		}
	}
	class SelectButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Select;
		static final String name="Select";
		static final String tipText="Select an object";
		public SelectButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetIconOn();
			}
		});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Select);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Select));
			} else {
				cho.setCurrentChoice(ButtonOption.Select);
				setOn(true);
				turnOn(ButtonOption.Select);
				setIcon(iconOnMap.get(ButtonOption.Select));
			}
			
		}
		
	}
	class LineButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Line;
		static final String name="Line";
		static final String tipText="Draw a straight line";
		public LineButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SetIconOn();
					// createNewItem();
					// repaint();
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Line);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Line));
			} else {
				cho.setCurrentChoice(ButtonOption.Line);
				//setOn(true);
				turnOn(ButtonOption.Line);
				setIcon(iconOnMap.get(ButtonOption.Line));
			}
		}
		
	}
	class NodeButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Node;
		static final String name="Node";
		static final String tipText="Draw a node";
		public NodeButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SetIconOn();
					// createNewItem();
					// repaint();
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Node);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Node));
			} else {
				cho.setCurrentChoice(ButtonOption.Node);
				//setOn(true);
				turnOn(ButtonOption.Node);
				setIcon(iconOnMap.get(ButtonOption.Node));
			}
			
		}
		
	}
	class ResizeButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Resize;
		static final String name="Resize";
		static final String tipText="Resize";
		public ResizeButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SetIconOn();
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Resize);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Resize));
			} else {
				cho.setCurrentChoice(ButtonOption.Resize);
				//setOn(true);
				turnOn(ButtonOption.Resize);
				setIcon(iconOnMap.get(ButtonOption.Resize));
			}
			
		}
		
	}
	class RefreshButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Refresh;
		static final String name="Refresh";
		static final String tipText="Refresh";
		public RefreshButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(Drawings d: GUIMain.topo.itemList){
						if(d.getShapeType()==ShapeType.NodeNode){
							if(d.configComplete())d.setColor(Drawings.nodeConfigedColor);
					    	else 
					    		d.setColor(Drawings.defaultNodeColor);
						}
						else if(d.getShapeType()==ShapeType.NodeLine){
							if(d.configComplete())d.setColor(Drawings.lineConfigedColor);
					    	else 
					    		d.setColor(Drawings.defaultLineColor);
						}
							
					}
					guimain.repaint();
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Refresh);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Refresh));
			} else {
				cho.setCurrentChoice(ButtonOption.Refresh);
				setOn(true);
				setIcon(iconOnMap.get(ButtonOption.Refresh));
			}
			
		}
	}
	class RequestButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Request;
		static final String name="Request";
		static final String tipText="Generating Requests";
		public RequestButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SetIconOn();
				}
			});
			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			super.SetIconOn();
			if (isOn()){ // flip
				cho.unsetCurrentChoice(ButtonOption.Request);
				setOn(false);
				setIcon(iconOffMap.get(ButtonOption.Request));
			} else {
				cho.setCurrentChoice(ButtonOption.Request);
				//setOn(true);
				turnOn(ButtonOption.Request);
				setIcon(iconOnMap.get(ButtonOption.Request));
			}
		}
		
	}
	class ComputeButton extends Button{
		private static final long serialVersionUID = 1L;
		final ButtonOption bo=ButtonOption.Compute;
		static final String name="Compute";
		static final String tipText="Compute optimal soltuion";
		public ComputeButton(String t, Icon i){
			super(t,i);
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 Runnable r = new Runnable() {
				            public void run() {
				            	if(!isOn()){
					            	SetIconOn();
					            	try{
									GUIMain.solve(CHOICES.getComputeMode(),CHOICES.getObjective());
					            	}
					            	catch(Exception e){
					            		e.printStackTrace();
					            		setOn(false);
					            		setIcon(iconOffMap.get(ButtonOption.Compute));
					            	}
									setOn(false);
									setIcon(iconOffMap.get(ButtonOption.Compute));
				            	}
				            }
				        };
				 
				        Thread t = new Thread(r);
				        // Lets run Thread in background..
				        // Sometimes you need to run thread in background for your Timer application..
				        t.start(); // starts thread in background..
				        
				}
			});

			setToolTipText(getTipText());
			setBorderPainted(false);
		}
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getTipText() {
			return tipText;
		}

		@Override
		public void SetIconOn() {

			//super.SetIconOn();
			if (isOn()){ // don not interrupt while computing
			} else {
				//cho.setCurrentChoice(ButtonOption.Compute);
				setOn(true);
				setIcon(iconOnMap.get(ButtonOption.Compute));
			}
			
		}
	}
	LinkedHashMap<ButtonOption, Button> buttonMap=Maps.newLinkedHashMap();
	{
		buttonMap.put(ButtonOption.New, new NewButton("",iconOffMap.get(ButtonOption.New)));
		buttonMap.put(ButtonOption.Open, new OpenButton("",iconOffMap.get(ButtonOption.Open)));
		buttonMap.put(ButtonOption.Save, new SaveButton("",iconOffMap.get(ButtonOption.Save)));
		buttonMap.put(ButtonOption.Select, new SelectButton("",iconOnMap.get(ButtonOption.Select)));
		buttonMap.put(ButtonOption.Line, new LineButton("",iconOffMap.get(ButtonOption.Line)));
		buttonMap.put(ButtonOption.Node, new NodeButton("",iconOffMap.get(ButtonOption.Node)));
		buttonMap.put(ButtonOption.Resize, new ResizeButton("",iconOffMap.get(ButtonOption.Resize)));
		buttonMap.put(ButtonOption.Refresh, new RefreshButton("",iconOffMap.get(ButtonOption.Refresh)));
		buttonMap.put(ButtonOption.Request, new RequestButton("",iconOffMap.get(ButtonOption.Request)));
		buttonMap.put(ButtonOption.Compute, new ComputeButton("",iconOffMap.get(ButtonOption.Compute)));
	}
	 private void turnOn (ButtonOption c){
		switch (c){
			case Select:
				buttonMap.get(ButtonOption.Select).setOn(true);
				buttonMap.get(ButtonOption.Line).setOn(false);
				buttonMap.get(ButtonOption.Node).setOn(false);
				buttonMap.get(ButtonOption.Resize).setOn(false);
				buttonMap.get(ButtonOption.Request).setOn(false);
				break;
			case Line:
				buttonMap.get(ButtonOption.Select).setOn(false);
				buttonMap.get(ButtonOption.Line).setOn(true);
				buttonMap.get(ButtonOption.Node).setOn(false);
				buttonMap.get(ButtonOption.Resize).setOn(false);
				buttonMap.get(ButtonOption.Request).setOn(false);
				break;
			case Node:
				buttonMap.get(ButtonOption.Select).setOn(false);
				buttonMap.get(ButtonOption.Line).setOn(false);
				buttonMap.get(ButtonOption.Node).setOn(true);
				buttonMap.get(ButtonOption.Resize).setOn(false);
				buttonMap.get(ButtonOption.Request).setOn(false);
				break;
			case Resize:
				buttonMap.get(ButtonOption.Select).setOn(false);
				buttonMap.get(ButtonOption.Line).setOn(false);
				buttonMap.get(ButtonOption.Node).setOn(false);
				buttonMap.get(ButtonOption.Resize).setOn(true);
				buttonMap.get(ButtonOption.Request).setOn(false);
				break;
			case Request:
				buttonMap.get(ButtonOption.Select).setOn(false);
				buttonMap.get(ButtonOption.Line).setOn(false);
				buttonMap.get(ButtonOption.Node).setOn(false);
				buttonMap.get(ButtonOption.Resize).setOn(false);
				buttonMap.get(ButtonOption.Request).setOn(true);
			default:
				break;
				
		}
	}
	public ButtonPan(final GUIMain t, final CHOICES ch) {
		cho = ch;
		guimain=t;
		// buttonPanel = new JToolBar(JToolBar.VERTICAL);
		buttonPanel = new JToolBar(SwingConstants.HORIZONTAL);
		for(Button b:buttonMap.values()){
			buttonPanel.add(b);
		}
		turnOn(ButtonOption.Select);
		cho.setCurrentChoice(ButtonOption.Select);
		
	}
}