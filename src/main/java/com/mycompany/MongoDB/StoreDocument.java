/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MongoDB;


import com.mongodb.BasicDBObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import com.mongodb.MongoClient;
import static com.mycompany.WordNet.TagText.TaggingToTweetText;
import static com.mycompany.WordNet.TagText.cleanText;

import java.util.Date;


/**
 *
 * @author Danyal-PC
 */
public class StoreDocument {
    
        private static MongoClient mongoClient;
        private static DBCollection coll;
        private static DBCursor cursor;
//        private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        private static Calendar cal = Calendar.getInstance(); 
        
    public static void connectToMongo(final String node,
                                      final int port ,
                                      final String collection)
    {
    
         // To connect to mongodb server
         mongoClient = new MongoClient( node , port );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "twitterdb" );
         
        //  coll_tweet = db.getCollection("tweets");
          coll = db.getCollection(collection);
        
    }
    
    public static void disconnectToMongo()
    {
    mongoClient.close();
    
    }
    
    public static DBCursor getTweetTextMongo(String collection){
    
    
               // To connect to mongodb server
          mongoClient = new MongoClient( "127.0.0.1" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "twitterdb" );
//       
         cursor = db.getCollection(collection).find();
         return cursor; 
    }
    
    public static DBCursor getTweetMongo(String collection)
    {

               // To connect to mongodb server
          mongoClient = new MongoClient( "127.0.0.1" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "twitterdb" );
//       
         cursor = db.getCollection(collection).find(new BasicDBObject(), new BasicDBObject("_id", 0));
         return cursor;
    }
 
        public static void storeResults(int count,
                                        float happy,
                                        float  sad,
                                        float joy ,
                                        float fear ,
                                        int total)
        {
              try{  			
         BasicDBObject doc = new BasicDBObject().
            append("Created_At",new Date()).
            append("TotalTweet", count).
            append("Happy",happy).
            append("Sad", sad).
            append("Joy", joy).
            append("Fear",fear).
            append("Neutral",total);
				
          coll.insert(doc);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
        }
    
        public static void storeTagSynsets(String happy,
                                           String sad,
                                           String fear,
                                           String joy)
    {
      try{  			
         BasicDBObject doc = new BasicDBObject().
            append("Created_At",new Date()).
            append("Happy_Tag",happy).
            append("Sad_Tag", sad).
            append("Joy_Tag", joy).
            append("Fear_Tag",fear);
				
          coll.insert(doc);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
    
    }
    
        public static void storeSynsets(String happy,
                                        String sad,
                                        String fear,
                                        String joy)
    {
      try{  			
         BasicDBObject doc = new BasicDBObject().
            append("Created_At",new Date()).
            append("Happy",happy).
            append("Sad", sad).
            append("Joy", joy).
            append("Fear",fear);
				
          coll.insert(doc);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
    
    }
        
        public static void storeTweet(String id ,
                                      String tweet ,
                                      String loc ,
                                      String countryCode,
                                      String SC_Name,
                                      Date date ,
                                      String source ,
                                      String lang ) 
        {
            String Tag_Text="";
             if(lang.equals("en"))
                Tag_Text =TaggingToTweetText(tweet);
      try{  			
         BasicDBObject doc = new BasicDBObject().
            append("ID", id).
            append("Text",cleanText(tweet)).
            append("Location", loc).
            append("CountryCode",countryCode).
            append("ScreenName", SC_Name).
            append("Date",date).
            append("Source",source).
            append("Text_Taged",Tag_Text).
            append("Language",lang);
				
         coll.insert(doc);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
    
        
        public static void storeLanguages(int lang_english,
                                          int lang_Spanish,
                                          int lang_German,
                                          int lang_France,
                                          int lang_Italian,
                                          int lang_Turkish,
                                          int lang_Arabic,
                                          int lang_Japan,
                                          int lang_china,
                                          int lang_Undefind,
                                          int lang_Urdu,
                                          int lang_Thailand,
                                          int lang_Russian,
                                          int lang_Portugese)
        {

         try{  			
         BasicDBObject Language = new BasicDBObject().
            append("Created_At",new Date()).
            append("Lang_english",lang_english).
            append("Lang_Spanish",lang_Spanish).
            append("Lang_German", lang_German).
            append("Lang_France", lang_France).
            append("Lang_Italian",lang_Italian).
            append("Lang_Turkish",lang_Turkish).
            append("Lang_Arabic", lang_Arabic).
            append("Lang_Japan",  lang_Japan).
            append("Lang_china",  lang_china).
            append("Lang_Undefind", lang_Undefind).
            append("Lang_Thailand", lang_Thailand).
            append("Lang_Russian",  lang_Russian).
            append("Lang_Portugese",lang_Portugese);
				
         coll.insert(Language);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
        
        }
    
        public static void storeCounties(int country_pak,
                                         int continent_Asian,
                                         int continent_African,
                                         int continent_Oceania,
                                         int continent_NorthAmerica,
                                         int continent_SouthAmerica,
                                         int continent_European,
                                         int continent_Antarctica
                                                     )
        {

        try{  			
         BasicDBObject country = new BasicDBObject().
            append("Created_At",new Date()).
            append("Country_Pakistan",country_pak).
            append("Continent_Asian",  continent_Asian).
            append("Continent_African",continent_African).
            append("Continent_Oceania",continent_Oceania).
            append("Continent_NorthAmerica",continent_NorthAmerica).
            append("Continent_SouthAmerica",continent_SouthAmerica).
            append("Continent_European",continent_European).
            append("Continent_Antarctica", continent_Antarctica);
				
         coll.insert(country);

      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
        
        
        }
        
}
