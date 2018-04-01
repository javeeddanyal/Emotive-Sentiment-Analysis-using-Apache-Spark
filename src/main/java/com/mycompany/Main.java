/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import static com.mycompany.MongoDB.MongoToRDD.saveToES;
import com.mycompany.WordNet.Twokenize;
import static com.mycompany.MongoDB.StoreDocument.connectToMongo;
import static com.mycompany.MongoDB.StoreDocument.disconnectToMongo;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.spark.SparkException;

import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 *
 * @author Danyal-PC
 */
public class Main {
   
    public static final String TXT_FILE="Data/tweetsText.txt";
    public static String Tweet_File = "Data/Tweets.json";
    public static String Results_FILE="Data/Results.json";
    public static String Continent_FILE="Data/Country.json";
    public static String Language_FILE="Data/Language.json";
   
    public static void stopSparkContext(JavaStreamingContext jsc)
    {
      jsc.stop();
    }
        public static String ArrayToString(ArrayList<String> arr) {

	// Convert the ArrayList into a String.
	  String res = String.join(" ", arr);
          return res;
    }
    
    public static void main(String[] args) throws IOException, SparkException  
    {
        System.out.println("Start Java Main Class...");
        
       // :::::::::::::::::::::::::Step 1::::::::::::::::::::::::::::::::::::::::::
        // Run Real Time Twitter Strream with Spark
        // Tagging Tweet
        // Store fetch data  and tag_tweet into Mongodb.
        JavaStreamingContext jsc = com.mycompany.Streaming.RealTimeTwitterStream.run(60000);
        // Disconnect from mongodb
        disconnectToMongo();
        
        //::::::::::::::::::::::::Step 2::::::::::::::::::::::::::::::::::::::::::::
        // connect to mongodb to extract tweets 
        connectToMongo("127.0.0.1",27017,"TweeterData");
        // tweets writes to text file
        com.mycompany.MongoDB.MongoToFile.mongoDBTextToJSON(TXT_FILE,"TweeterData");
        // Disconnect from mongodb
        disconnectToMongo();
        
        //:::::::::::::::::::::::::Step 3::::::::::::::::::::::::::::::::::::::::::::
        
        // connect to mongodb to extract tweets
        connectToMongo("127.0.0.1",27017,"TweeterData");
        // Fetch results to JSON file
        com.mycompany.MongoDB.MongoToFile.mongoDBToJSON(Tweet_File , "TweeterData");
        // Disconnect from mongodb
        disconnectToMongo();

        //:::::::::::::::::::::::::Step 4::::::::::::::::::::::::::::::::::::::::::::        
        // search words from WordNet Liberary like happy , sad etc.
        com.mycompany.WordNet.FindSynsets.searchWord("happy","sad","fear","joy");
        // Synonyms match with Tweets Text           
        Twokenize.Tokenizations(TXT_FILE,
                                com.mycompany.WordNet.FindSynsets.Synsets_Happy,
                                com.mycompany.WordNet.FindSynsets.Synsets_Sad,
                                com.mycompany.WordNet.FindSynsets.Synsets_Fear,
                                com.mycompany.WordNet.FindSynsets.Synsets_Joy);
      // com.mycompany.Model.NaiveBayesExample.run();
        //:::::::::::::::::::::::::Step 5::::::::::::::::::::::::::::::::::::::::::::
        
        // connect to mongodb to extract tweets
        connectToMongo("127.0.0.1",27017,"Results");
        // Fetch results to JSON file
        com.mycompany.MongoDB.MongoToFile.mongoDBToJSON(Results_FILE , "Results");
        // Disconnect from mongodb
        disconnectToMongo();

        //:::::::::::::::::::::::::Step 6:::::::::::::::::::::::::::::::::::::::::::::
        // connect to mongodb to Manipulate Information 
        connectToMongo("127.0.0.1",27017,"TweeterData");
        // Useful Information store to MongoDB Information Collection
        com.mycompany.MongoDB.MongoToFile.mongoDBRawManipulate("TweeterData");
        // Disconnect from mongodb
        disconnectToMongo();
      
        //:::::::::::::::::::::::::::Step 7:::::::::::::::::::::::::::::::::::::::::::
        // connect to mongodb to extract Languages
        connectToMongo("127.0.0.1",27017,"Languages");
        // Fetch Languages to JSON file
        com.mycompany.MongoDB.MongoToFile.mongoDBToJSON(Language_FILE , "Languages");
        // Disconnect from mongodb
        disconnectToMongo();
       
        //:::::::::::::::::::::::::::Step 8:::::::::::::::::::::::::::::::::::::::::::
        // connect to mongodb to extract Countries
        connectToMongo("127.0.0.1",27017,"Continent");
        // Fetch Countries to JSON file
        com.mycompany.MongoDB.MongoToFile.mongoDBToJSON(Continent_FILE , "Continent");
        // Disconnect from mongodb
        disconnectToMongo();
      
        //:::::::::::::::::::::::::::::Step 9::::::::::::::::::::::::::::::::::::::::
        // Save Emotion json to emotion index on Elastic search
        saveToES(Tweet_File,"tweet","tweets");

        //:::::::::::::::::::::::::::::Step 10::::::::::::::::::::::::::::::::::::::::
        // Save Emotion json to emotion index on Elastic search
        saveToES(Results_FILE,"emotion","tweets");
        
        //:::::::::::::::::::::::::::::Step 11::::::::::::::::::::::::::::::::::::::::
        //save Language Json to language index on Elastic Search
        saveToES(Language_FILE ,"language","tweets");
        
        //:::::::::::::::::::::::::::::Step 12:::::::::::::::::::::::::::::::::::::::
        //save Country Json to country index on Elastic Search
        saveToES(Continent_FILE ,"continent","tweets");
        
        //::::::::::::::::::::::::::::::Step 13::::::::::::::::::::::::::::::::::::::
        // stop JavaStreamingContext
        stopSparkContext(jsc);
        //:::::::::::::::::::::::::::: ENDDDDDD ::::::::::::::::::::::::::::::::::::::
       System.out.println("\nProgram Completed Successsfully\n");
        
    }

    
}
