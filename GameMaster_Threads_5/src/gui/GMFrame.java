package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GMFrame extends JFrame implements Runnable{
	public GMFrame(String frameTitle){
		super(frameTitle);
		
	}

	/*
	 * Suggestion to use the SwingUtilities.invokeLater discovered:
	 * http://stackoverflow.com/questions/12713563/java-swing-why-must-resize-frame-so-that-can-show-components-have-added
	 */
	@Override
	public void run() {
		//frame attributes
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		//finalize frame
	}
}
