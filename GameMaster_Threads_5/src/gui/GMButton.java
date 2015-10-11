package gui;

import games.GMActionList;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*
 * consider a global action blocking queue to create buttons that add to it automatically
 */

//this class rewrites buttons to have white blackgrounds, square black borders, and black centered font
public class  GMButton extends JButton implements GMActionList{
	private GMPanel toPanel;
	BlockingQueue<ActionEvent> actionQueue;
	
	//standard button with no actions
	public GMButton(String buttonText){
		setText(buttonText);
		setBackground(Color.WHITE);
		Border borderLine = new LineBorder(Color.BLACK);
		Border borderMargin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(borderLine, borderMargin);
		setBorder(compound);
		setBorderPainted(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
	}
	//standard button with action integer
	public GMButton(String buttonText, String actionCommand){
		setText(buttonText);
		setBackground(Color.WHITE);
		Border borderLine = new LineBorder(Color.BLACK);
		Border borderMargin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(borderLine, borderMargin);
		setBorder(compound);
		setBorderPainted(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setActionCommand(actionCommand);
	}
	//standard button with action integer
	public GMButton(String buttonText, final BlockingQueue<ActionEvent> actionQueue, String actionCommand){
		this.actionQueue = actionQueue;
		setText(buttonText);
		setBackground(Color.WHITE);
		Border borderLine = new LineBorder(Color.BLACK);
		Border borderMargin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(borderLine, borderMargin);
		setBorder(compound);
		setBorderPainted(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setActionCommand(actionCommand);
		addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action){
            	System.out.print("button " + getText() + " pressed. ac = " + action.getActionCommand());
                //Execute when button is pressed
            	actionQueue.add(action);
            }
        }); 
	}
	//standard button with action to next panel
	public GMButton(String buttonText, GMPanel toPanel){
		setText(buttonText);
		setBackground(Color.WHITE);
		Border borderLine = new LineBorder(Color.BLACK);
		Border borderMargin = new EmptyBorder(1, 1, 1, 1);
		Border compound = new CompoundBorder(borderLine, borderMargin);
		setBorder(compound);
		setBorderPainted(true);
		setHorizontalTextPosition(SwingConstants.CENTER);
		this.toPanel = toPanel;
		addActionListener(new ActionListener(){
			// When the button is pressed, go to the toFramee
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}
	/**
	 * @return the toFrame that the button follows to the next frame when pressed
	 */
	GMPanel getToPanel() {
		return toPanel;
	}
	/**
	 * @param toFrame the toFrame to set
	 */
	void setToPanel(GMPanel toPanel) {
		this.toPanel = toPanel;
	}
}//end GMButton
