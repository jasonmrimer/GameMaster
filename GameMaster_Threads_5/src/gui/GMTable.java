package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GMTable extends JTable {
	String[] columnNames;
	DefaultTableModel tableModel;
	public GMTable(String[] columnNames){ //feed in array of column names
		this.columnNames = columnNames;
		tableModel = new DefaultTableModel();
//		setDefaultRenderer(String.class, new ProxyCellRenderer(getDefaultRenderer(String.class)));
		setDefaultRenderer(Object.class, new MyRenderer());
		setBackground(Color.white);
		setForeground(Color.black);
		setRowHeight(150);
		setShowGrid(false);
		setShowHorizontalLines(true);
		setGridColor(Color.black);
		setSelectionForeground(Color.black);
		setSelectionBackground(Color.white);
		setCellSelectionEnabled(false);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		setAutoResizeMode(AUTO_RESIZE_SUBSEQUENT_COLUMNS);
//		setSelectionModel(newModel);research
		
		for (int column = 0; column < columnNames.length; column++){
			tableModel.addColumn(columnNames[column]);
		}
	}
	
	public DefaultTableModel getTableModel(){
		return tableModel;
	}
	
	/*
	 * To get rid of the blue border around a cell when selected,
	 * I researched this code from MadProgrammer:
	 * http://stackoverflow.com/questions/18801137/java-jtable-disable-single-cell-selection-border-highlight
	 */
	public class MyRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	        setBorder(noFocusBorder);
//	        setBorder(new LineBorder(Color.WHITE));
	        return this;
	    }

	}
}
