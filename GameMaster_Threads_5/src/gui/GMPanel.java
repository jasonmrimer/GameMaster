package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GMPanel extends JPanel{
	String frameTitle = "Missing Title from Panel";
	GMPanel backPanel;
	boolean hasBackPanel = false;
	public GMPanel(){
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 1));
	}
	public GMPanel(String frameTitle){
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 1));
		this.frameTitle = frameTitle;
	}
	public GMPanel(String frameTitle, GMPanel backPanel){
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 1));
		System.out.println("GMPanel constructor for " + frameTitle + " with backPanel " + backPanel.getFrameTitle());
		this.backPanel = backPanel;
		this.frameTitle = frameTitle;
		this.hasBackPanel = true;
	}
	public String getFrameTitle(){
		return frameTitle;
	}
	public GMPanel getBackPanel(){
		return backPanel;
	}
	public boolean hasBackPanel(){
		return hasBackPanel;
	}
}//endGMPanel
