package games;


import gui.GMButton;
import gui.GMPanel;
import gui.GMPanelGrid;
import gui.GMTable;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
 * Dungeons and Draons controls the D&D-specific functions of the application. Panels for separate areas such as
 * characters or equipment are created then passed to the currentPanel which is always displayed by the GUI class.
 */
public class DungeonsAndDragons implements GMActionList{
	GMPanel backPanel;
	GMPanel homePanel; //main, four-button panel for D&D
	GMPanel currentPanel;
	GMPanel characterPanel;

	BlockingQueue<ActionEvent> actionQueue; //pipeline to pass actions to the GameController
	
	//Constructor that passes in reference to the "global" pipeline as welll as the panel to navigate back to
	DungeonsAndDragons(BlockingQueue<ActionEvent> actionQueue, GMPanel backPanel){
		this.backPanel = backPanel;
		this.actionQueue = actionQueue;
		homePanel = createDungeonsAndDragonsHomePanel(); 
		currentPanel = homePanel; //set the current panel to the home panel, initially
	}
	//create the 4-button home panel upon construction of the D&D object
	private GMPanel createDungeonsAndDragonsHomePanel(){
		//construct homePanel
		homePanel = new GMPanel("Dungeons & Dragons", backPanel);
		//buttons
		GMButton gmbCharacter;
		GMButton gmbEquipment;
		GMButton gmbMonster;
		GMButton gmbCampaign;
		//grid panel
		GMPanelGrid gmpDAndDHomeButtonPanel = new GMPanelGrid(2, 2);
		//buttons create
		gmbCharacter = new GMButton("Character", actionQueue, ACTION_DUNGEONS_AND_DRAGONS_CHARACTERS);
		gmbEquipment = new GMButton("Equipment");
		gmbMonster = new GMButton("Monster");
		gmbCampaign = new GMButton("Campaign");
		//buttons add
		gmpDAndDHomeButtonPanel.add(gmbCharacter);
		gmpDAndDHomeButtonPanel.add(gmbEquipment);
		gmpDAndDHomeButtonPanel.add(gmbMonster);
		gmpDAndDHomeButtonPanel.add(gmbCampaign);
		//add grid to home 
		homePanel.add(gmpDAndDHomeButtonPanel);
		return homePanel;
	}//end createDungeonsAndDragonsHomePanel


	public void createCharacterPanel(){
		int num_characters = 10; //temporary number for testing - will be read-in from datbase
		characterPanel = new GMPanel("Characters", homePanel); //construct panel

		//to be used upon connection to database
		/*
		 * use with Character class and have two separate constructors: 
		 * 1 for quick display using the four columns for info 
		 * 2 for full characters created in the background while the user is choosing a character
		 */

		TreeMap<String, GMButton> characterGMButtonMap = new TreeMap<String, GMButton>(); 
		//more likely to use with the character name as the key
		for (int character = 0; character < num_characters; character++){
			characterGMButtonMap.put(String.valueOf(character), new GMButton(String.valueOf(character))); 
		}
		
		
		/* FUTURE
		 * when we import the list of character objects, create a dynamic ArrayList
		 * containing each character then add GMButtons for each character with ActionListeners
		 * created to each frame set for the character 
		 * *will sort by last-accessed first then alphabetical order
		 */
		String[] columnNames = {"UserID", "Character", "Class", "Level"}; //three primary columns (first is ALWAYS hidden from user)
		final GMTable gmtCharacterTable = new GMTable(columnNames);

		//create rows
		for(String key : characterGMButtonMap.keySet()){
			gmtCharacterTable.getTableModel().addRow(new Object[]{
					characterGMButtonMap.get(key).getText(), 
					String.valueOf((char)(new Random().nextInt(26) + 'a') + "" + (char)(new Random().nextInt(26) + 'a') //random name for testing
						+ "" + (char)(new Random().nextInt(26) + 'a')+ "" + (char)(new Random().nextInt(26) + 'a')),
					String.valueOf((char)(new Random().nextInt(26) + 'a') + "" + (char)(new Random().nextInt(26) + 'a') //random class for testing
						+ "" + (char)(new Random().nextInt(26) + 'a')+ "" + (char)(new Random().nextInt(26) + 'a')),
					String.valueOf(new Random().nextInt(20))
			});
		}
		gmtCharacterTable.setModel(gmtCharacterTable.getTableModel());
		gmtCharacterTable.getColumnModel().removeColumn(gmtCharacterTable.getColumnModel().getColumn(0));;//hide userID column

		//row selection action
		/*
		 * To act on a row selection, use code from 
		 * http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#modelchange
		 */
		gmtCharacterTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						System.out.println("row " + gmtCharacterTable.getSelectedRow() + " selected.");	
						int userID = Integer.valueOf((String) gmtCharacterTable.getTableModel().getValueAt(gmtCharacterTable.getSelectedRow(), 0));
						ActionEvent event = new ActionEvent(e, userID, ACTION_DUNGEONS_AND_DRAGONS_CHARACTERS_DISPLAY_CHARACTER);
						actionQueue.add(event);
					}
				});
		//set the table to a scroll pane then add the scrollpane to the panel
		characterPanel.add(new JScrollPane(gmtCharacterTable));
	}
	//accessors
	public GMPanel getCharacterPanel() {
		return characterPanel;
	}
	public GMPanel getCurrentPanel() {
		return currentPanel;
	}
	//mutators
	public void setCurrentPanel(GMPanel currentPanel) {
		this.currentPanel = currentPanel;
	}
}