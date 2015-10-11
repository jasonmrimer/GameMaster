import java.awt.event.ActionEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.*;
import games.*;
/*
 * Main class to start the program and handle threads
 * 
 * The program is designed in four parts:
 * GUI<>GameController<>Database<>Server
 * GUI displays and interacts with user
 * GameController interprets actions from GUI, receives data from Database, and returns manipulated information to GUI
 * Database retrieves from GameController and Server and returns data to and from
 * Server gets data to and from local databases and maintains all user accounts
 */
public class Main {
	static ExecutorService executor;
	public GUI gui2;
	public static void main(String[] args){
		//these 3 queues are passed to gameController that uses the queues to trigger events and hold panels
		BlockingQueue<GMPanel> gmpQueue = new ArrayBlockingQueue<>(1);
		BlockingQueue<ActionEvent> actionQueue = new ArrayBlockingQueue<ActionEvent>(1);
		GUI gui = new GUI(gmpQueue, actionQueue);
		GameController gameController = new GameController(gmpQueue, actionQueue);
		executor = Executors.newFixedThreadPool(4);
		executor.execute(gui);
		executor.execute(gameController);
	}
	
}
