package games;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import gui.*;

/*
 * This class receives input from the local database, interprets the data,
 * then sends it to the GUI.
 * Handles ALL Actions
 */

public class GameController implements Runnable, GMActionList{
	BlockingQueue<GMPanel> gmpQueue;
	BlockingQueue<ActionEvent> actionQueue;
	GMPanel gmpGCHome;
	DungeonsAndDragons dungeonsAndDragons;
	public GameController(){
		System.out.println("GMError: GameController empty constructor");
	}
	//constructor with pipelines
	public GameController(BlockingQueue<GMPanel> gmpQueue, final BlockingQueue<ActionEvent> actionQueue){
		this.gmpQueue = gmpQueue;
		this.actionQueue = actionQueue;
		
		///create the home panel for the game controller
		gmpGCHome = new GMPanel("Game Master");
//		GMButton gmbDAndD = new GMButton("D&D", actionQueue, aSTART_DUNGEONS_AND_DRAGONS); //not working, throwing null pointer
		GMButton gmbDAndD = new GMButton("D&D", ACTION_START_DUNGEONS_AND_DRAGONS);
		gmbDAndD.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent action) {
				System.out.println("D&D button with action: " + action.getActionCommand());
				actionQueue.add(action);
			}
		});
		GMButton gmbPathfinder = new GMButton("Pathfinder");
		GMButton gmbTraveller = new GMButton("Traveller");
		GMButton gmbCallOfCthuhlu = new GMButton("Call of Cthuhlu");
		GMButton gmbHackmaster = new GMButton("Hackmaster");
		GMButton gmbShadowrun = new GMButton("Shadowrun");
		GMButton gmbDunes = new GMButton("Dunes");
		GMButton gmbReserved1 = new GMButton("Reserved1");
		GMButton gmbReserved2 = new GMButton("Reserved2");
		//add all to grid panel
		GMPanel gmpGridPanel = new GMPanelGrid(3, 3);
		gmpGridPanel.add(gmbDAndD);
		gmpGridPanel.add(gmbPathfinder);
		gmpGridPanel.add(gmbTraveller);
		gmpGridPanel.add(gmbCallOfCthuhlu);
		gmpGridPanel.add(gmbHackmaster);
		gmpGridPanel.add(gmbShadowrun);
		gmpGridPanel.add(gmbDunes);
		gmpGridPanel.add(gmbReserved1);
		gmpGridPanel.add(gmbReserved2);
		//add grid to whole
		gmpGCHome.add(gmpGridPanel);
		//send the panel to the queue to change screens
		gmpQueue.add(gmpGCHome);
	}

	
	/* ACTION AREA */
	//thread to listen for actions
	@Override
	public void run(){
		while (true){
			//Game controller is always listening for actions from the GUI
			while (!actionQueue.isEmpty()){
				performAction(actionQueue.remove());
			}
		}
	}
	
	
	//handle all actions inside the program
	private void performAction(ActionEvent action) {
		//handle the beginning of all games by creating a front screen while loading the back end behind it
		/*
		 * consider using a constructor system for buttons that incorporate by integer what type of button
		 * and for what game to maintain consistency of variables across classes
		 */

		switch (action.getActionCommand()){
			//start each game
			case ACTION_START_DUNGEONS_AND_DRAGONS:
				dungeonsAndDragons = new DungeonsAndDragons(actionQueue, gmpGCHome);
				gmpQueue.add(dungeonsAndDragons.currentPanel);
				System.out.println("actions to open D&D works and gmpGCHome = " + gmpGCHome.getName());
				break;
				
			//dungeons and dragons
			case ACTION_DUNGEONS_AND_DRAGONS_CHARACTERS:
				dungeonsAndDragons.createCharacterPanel();
				dungeonsAndDragons.setCurrentPanel(dungeonsAndDragons.characterPanel);
				gmpQueue.add(dungeonsAndDragons.currentPanel);
				System.out.println("gc d&D characters");
				break;	
			//characters
			case ACTION_DUNGEONS_AND_DRAGONS_CHARACTERS_DISPLAY_CHARACTER:
				System.out.println("display character with userID " + action.getID());

		}
		
	}
	
}
