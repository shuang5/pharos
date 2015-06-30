package org.renci.pharos.lp;

import java.awt.FileDialog;
import java.awt.Frame;

class FileDiag {
	public String open(String title){
		Frame f = new Frame();
		FileDialog fd1 = new FileDialog(f, title, FileDialog.LOAD);
		fd1.setVisible(true);

		String fDirectory = fd1.getDirectory();
		String fFile = fd1.getFile();

		f.dispose();
		return (fDirectory!=null && fFile!=null)?fDirectory+
				 System.getProperty("file.separator") + fFile:null; 
		//filechooser hangs after upgrading JRE to 1.8. think there is a bug
		/*
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle(title);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
		    return null;
		}
		File fileName = fileChooser.getSelectedFile();
		fileName.canRead();
		if (fileName == null || fileName.getName().equals("")) {
		    JOptionPane.showMessageDialog(fileChooser, "Invalid File Name",
		            "Invalid File Name", JOptionPane.ERROR_MESSAGE);
		
		}
		return fileName.getAbsolutePath();
		*/
		
	}
}