package database;

//This is the backend architecture for GameMaster. 
//Created by: Dave Walters
//Date updated: 5/30/2014

import java.sql.*;
import java.util.*;

public class GameMasterSQL {
   
  //constructor  
    public GameMasterSQL (){} 
    
  //Takes username and password input, runs it against the 'users' table in the
  //database. If found, the query will return the userid. If not found, will return
  //0. In the GUI, can make an if statement that displays a login failed message
  //and call recursion.
    public int login (Statement GameMasterQuery, String username, String password) throws SQLException {
        
        ResultSet userCredentials;
        int userid = 0;
        
        userCredentials = GameMasterQuery.executeQuery ("SELECT *"
            + " FROM users"
            + " WHERE email = '" + username + "'"
            + " AND password = '" + password + "';");
        
         if (userCredentials.next()){
            userid = Integer.parseInt(userCredentials.getString("userid"));
         } //end if
         
        return userid;        
    } //end login()
    
  //Takes userid int and queries the 'userchars' table to determine which charids
  //are assigned to that userid. It will return an ArrayList with those numbers for
  //further processing.
    public ArrayList getUserCharArrayList (Statement GameMasterQuery, int userid, String gameString) throws SQLException {
        
        ResultSet userCharResultSet;
        ArrayList userCharArrayList = new ArrayList<Integer>();
        
        userCharResultSet = GameMasterQuery.executeQuery ("SELECT userchars.charid"
            + " FROM characters INNER JOIN userchars"
            + " WHERE characters.game = '" + gameString + "'"
            + " AND userchars.charid = characters.charid"
            + " and userchars.userid = " + userid + ";");
        
        while (userCharResultSet.next()){
            userCharArrayList.add(Integer.parseInt(userCharResultSet.getString("charid")));
        } //end while statement
        
        return userCharArrayList;
    } //end getUserCharArrayList()
    
    public ArrayList getCharacterNames (Statement GameMasterQuery, ArrayList userCharArray) throws SQLException {
        
        ResultSet charNamesResultSet;
        ArrayList characterNames = new ArrayList<>();
        
        for (int i = 0; i < userCharArray.size(); i++) {
            charNamesResultSet = GameMasterQuery.executeQuery("SELECT name"
                + " FROM characters"
                + " WHERE charid = " + userCharArray.get(i));
        
            while (charNamesResultSet.next()){
                characterNames.add(charNamesResultSet.getString("name"));
            }//end while statement
        } //end for statement
   
        return characterNames; 
    } //end getCharacterNames()
    
    public HashMap getCharacterStats (Statement GameMasterQuery, int charid) throws SQLException {
        
        ResultSet statResultSet;
        HashMap characterStats = new HashMap<>();
        
        statResultSet = GameMasterQuery.executeQuery("SELECT stats.str, stats.dex,"
                + " stats.con, stats.int, stats.wis, stats.cha, stats.level,"
                + " characters.race, characters.class"
                + " FROM stats INNER JOIN characters"
                + " WHERE stats.charid = " + charid
                + " AND characters.charid = " + charid
                + " AND stats.charid = characters.charid");
        
        while (statResultSet.next()){
            characterStats.put("STR", Integer.parseInt(statResultSet.getString("str")));
            characterStats.put("DEX", Integer.parseInt(statResultSet.getString("dex")));
            characterStats.put("CON", Integer.parseInt(statResultSet.getString("con")));
            characterStats.put("INT", Integer.parseInt(statResultSet.getString("int")));
            characterStats.put("WIS", Integer.parseInt(statResultSet.getString("wis")));
            characterStats.put("CHA", Integer.parseInt(statResultSet.getString("cha")));
            characterStats.put("LEVEL", Integer.parseInt(statResultSet.getString("level")));
            characterStats.put("RACE", statResultSet.getString("race"));
            characterStats.put("CLASS", statResultSet.getString("class"));
        } //end while
        
        return characterStats;

    } //end getCharacterStats()
    
    
    
  //Connects to the MySQL database  
    public Connection getConnection () throws SQLException {
               
        loadDriver();
        Connection GameMasterConnection = DriverManager.getConnection("jdbc:mysql://localhost:"
                + "3306/gamemaster?user=root&password=Aliens123");
        return GameMasterConnection; 
    } //end getConnection()
    
  //Loads the Classpath driver.  
    private void loadDriver () {
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println ("LoadDriver.java: ClassNotFoundException");
        } catch (InstantiationException i) {
            System.out.println ("LoadDriver.java: InstantiationException");            
        } catch (IllegalAccessException ie){
            System.out.println ("LoadDriver.java: IllegalAccessException");
        }
    } //end loadDriver()
    
} //end GameMasterSQL class
