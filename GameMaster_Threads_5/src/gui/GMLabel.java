package gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GMLabel extends JLabel{
	public GMLabel(String labelText){
		setText(labelText);
		setBackground(Color.WHITE);
		Border borderLine = new LineBorder(Color.RED);
		Border borderMargin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(borderLine, borderMargin);
		setBorder(compound);
		setHorizontalTextPosition(SwingConstants.CENTER);
	}
}//end GMLabel
