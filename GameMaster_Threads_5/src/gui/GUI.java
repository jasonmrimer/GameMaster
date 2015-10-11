package gui;
import games.GMActionList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI implements Runnable, GMActionList{
	private GMFrame mainFrame; //only window
	private GMPanel contentPane; //non-resizeable panel
	private GMPanel mainPanel; //changing panel to current view and placed in contentPane
	BlockingQueue<GMPanel> gmpQueue;
	BlockingQueue<ActionEvent> actionQueue;
	private GMPanel gmpHomePanel;

	public GUI(){
		createMainFrame();
	}
	//constructor with pipelines
	public GUI(BlockingQueue<GMPanel> gmpQueue, BlockingQueue<ActionEvent> actionQueue){
		createMainFrame();
		SwingUtilities.invokeLater((Runnable) mainFrame);
		this.gmpQueue = gmpQueue;
		this.actionQueue = actionQueue;
	}
	@Override
	public void run(){
		while (true){
			//maintain pipeline for receiving panels
			while (!gmpQueue.isEmpty()){
				changePanel(gmpQueue.remove());
			}
		}
	}
	
	void createMainFrame(){
		mainFrame = new GMFrame("Game Master");
		//frame attributes
		mainPanel = new GMPanel();
		contentPane = new GMPanel();
		Dimension dimension = new Dimension(800, 600);
		contentPane.setPreferredSize(dimension);
		contentPane.setSize(dimension);
//		mainFrame.add(mainPanel);
		contentPane.add(mainPanel);
		mainFrame.setContentPane(contentPane);
		mainFrame.setResizable(false);
		mainFrame.pack();
		//show
		mainFrame.setVisible(true);
	    
		
		/*
	     * used keybindings code from Metal Wing at http://stackoverflow.com/questions/10822787/binding-key-combination-to-jframe
	     */
		mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_LEFT), "BackAction");
		KeyStroke leftKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
		Action backAction = new AbstractAction() {
	         public void actionPerformed(ActionEvent e) {
	        	 if (mainPanel.hasBackPanel()){
		        	 System.out.println(mainPanel.getFrameTitle() + " has backPanel " + mainPanel.getBackPanel().getFrameTitle());
	        		 gmpQueue.add(mainPanel.getBackPanel());
	        	 }
	        	 System.out.println("backPanel complete");
	         }
		};		
		mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStroke, "BACK_ACTION");
		mainFrame.getRootPane().getActionMap().put("BACK_ACTION", backAction);
	}
	
	//transition between panels
	private void changePanel(GMPanel setPanel){
		mainPanel = setPanel;
		System.out.println("changePanel working, panel name: " + mainPanel.getFrameTitle());
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.revalidate();
		mainFrame.setTitle(mainPanel.getFrameTitle());
//		mainFrame.setContentPane(mainPanel);
//		mainFrame.add(mainPanel);
//		this.mainFrame.setSize(800, 600);
//		mainFrame.setResizable(false);
		mainFrame.pack();
		//show
//		mainFrame.setVisible(true);
	}
	
}