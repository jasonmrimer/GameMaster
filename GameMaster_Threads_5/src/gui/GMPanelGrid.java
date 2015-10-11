package gui;

import java.awt.Color;
import java.awt.GridLayout;

//create quick grid panels with white backgrounds
public class GMPanelGrid extends GMPanel{
	public GMPanelGrid(int rows, int columns){
		setBackground(Color.WHITE);
		setLayout(new GridLayout(rows, columns));
	}
	public GMPanelGrid(String title, int rows, int columns){
		super(title);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(rows, columns));
	}
	public GMPanelGrid(String title, GMPanel backPanel, int rows, int columns){
		super(title, backPanel);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(rows, columns));
	}
}//end GMPanelGrid	