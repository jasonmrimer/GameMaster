package database;


import java.sql.*;
import java.util.*;

//The purpose is to test GameMasterSQL functions. All logic in here will be handled
//by the GUI.

public class Main {
    
    public static void main() throws SQLException {
        
        
      //initializations  
        Scanner input = new Scanner (System.in);
        int userid = 0;
        int activeCharID;
        String gameString;
        ArrayList userCharArray = new ArrayList<>();
        ArrayList characterNames = new ArrayList<>();
        HashMap characterStats = new HashMap<>();
        int charid;
        
      //constructor  
        GameMasterSQL GMSQL = new GameMasterSQL ();
        
      //the connection only needs to be made in the GUI.
        Connection GameMasterConnection = GMSQL.getConnection();
        
      //the statement will be passed to each function.  
        Statement GameMasterQuery = GameMasterConnection.createStatement();
        
//----------------------------------------------------------------------------\\
//----------------------------login() FUNCTION TEST---------------------------\\        
        String username, password;
        
      //Will keep running the login function until a successful login is reached. 
      //How should we handle this in the actual program? 
        while (userid == 0){        
            System.out.print("Enter your email: ");
            username = input.nextLine();
        
            System.out.print ("Enter your password: ");
            password = input.nextLine();
            
            userid = GMSQL.login(GameMasterQuery, username, password);
            
            if (userid == 0) {
                System.out.println ("Login failed.");
            } //end if
            else {
                System.out.println ("Your user ID is: " + userid);
            } //end else
        } //end while
//-----------------------END OF login() FUNCTION TEST-------------------------\\
//----------------------------------------------------------------------------\\
        
        
        
//----------------------------------------------------------------------------\\
//--------------------getUserCharArray() FUNCTION TEST------------------------\\
        System.out.println ("Calling getUserCharArray()");
        System.out.print ("What game would you like to play? ('DD' for Dungeons"
                + " and Dragons or 'SR' for Shadow Run): ");
        gameString = input.next();
        userCharArray = GMSQL.getUserCharArrayList(GameMasterQuery, userid, gameString);
        if (userCharArray.isEmpty()){
            System.out.println ("No characters for " + gameString);
        } //end if
//------------------END OF getUserCharArray() FUNCTION TEST-------------------\\
//----------------------------------------------------------------------------\\

        
        
//----------------------------------------------------------------------------\\
//--------------------getCharacterNames() FUNCTION TEST-----------------------\\
        System.out.println ("Calling getCharacterNames()");
        characterNames = GMSQL.getCharacterNames(GameMasterQuery, userCharArray);
        for (int i = 0; i < userCharArray.size(); i++) {
            System.out.println (userCharArray.get(i) + ": " + characterNames.get(i));
        } //end for loop
//------------------END OF getCharacterNames() FUNCTION TEST------------------\\
//----------------------------------------------------------------------------\\
        
        
        
//----------------------------------------------------------------------------\\
//--------------------getCharacterStats() FUNCTION TEST-----------------------\\
        System.out.println ("Calling getCharacterStats()");
        System.out.print ("Which character's stats would you like to view? ");
        charid = Integer.parseInt(input.next());
        characterStats = GMSQL.getCharacterStats(GameMasterQuery, charid);
        System.out.println ("STR: " + characterStats.get("STR"));
        System.out.println ("DEX: " + characterStats.get("DEX"));
        System.out.println ("CON: " + characterStats.get("CON"));
        System.out.println ("INT: " + characterStats.get("INT"));
        System.out.println ("WIS: " + characterStats.get("WIS"));
        System.out.println ("CHA: " + characterStats.get("CHA"));
        System.out.println ("LEVEL: " + characterStats.get("LEVEL"));
        System.out.println ("RACE: " + characterStats.get("RACE"));
        System.out.println ("CLASS: " + characterStats.get("CLASS"));
//------------------END OF getCharacterNames() FUNCTION TEST------------------\\
//----------------------------------------------------------------------------\\
        
        
        
    } //end main

    
} //end Main.java
