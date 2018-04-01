/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Model;

import static com.mycompany.Main.TXT_FILE;
import static com.mycompany.Streaming.RealTimeTwitterStream.sparkContext;
import static com.mycompany.WordNet.FindSynsets.Synsets_Fear;
import static com.mycompany.WordNet.FindSynsets.Synsets_Happy;
import static com.mycompany.WordNet.FindSynsets.Synsets_Joy;
import static com.mycompany.WordNet.FindSynsets.Synsets_Sad;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

/**
 *
 * @author Danyal-PC
 */
public class NaiveBayes {
    
       private static long count_Happy;
       private static long count_Sad;
       private static long count_Fear;
       private static long count_Joy;
       private static int count;
               
   public static void run() {
       
            count_Happy = 0;
            count_Sad = 0;
            count_Fear = 0;
            count_Joy = 0;
            count = 0;
       
    JavaRDD<String> logData = sparkContext.textFile(TXT_FILE).cache();
         
     logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) {
          count++;
      String[] toks = s.split("\\s");
      for (int i=0; i<toks.length; i++) {
              if( Synsets_Happy.contains(toks[i]));
                   count_Happy++;
              if( Synsets_Sad.contains(toks[i]));
                   count_Sad++;
              if( Synsets_Fear.contains(toks[i]));
                   count_Fear++;
              if( Synsets_Joy.contains(toks[i]));
                   count_Joy++;
      }
       return true;
      }
    });

         count_Happy = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) { 
          return  s.contains(com.mycompany.WordNet.FindSynsets.Synsets_Happy.toString());

      }
    }).count();
     
    count_Sad = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) { 
          return  s.contains(com.mycompany.WordNet.FindSynsets.Synsets_Sad.toString());
      }
    }).count();

    count_Fear = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) { 
          return s.contains(com.mycompany.WordNet.FindSynsets.Synsets_Fear.toString());
      }
    }).count();

    count_Joy = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) {
                 return s.contains(com.mycompany.WordNet.FindSynsets.Synsets_Joy.toString());       
      }
    }).count();
    
        float result= count_Happy + count_Sad + count_Fear + count_Joy;
        float happy = (count_Happy / result) * 100;
       
        float sad = (count_Sad / result) * 100;
        float fear = (count_Fear / result) *100;
        float joy = (count_Joy / result) * 100;
        
        com.mycompany.MongoDB.StoreDocument.connectToMongo("127.0.0.1",27017,"Results");
        com.mycompany.MongoDB.StoreDocument.storeResults(count, happy, sad, joy, fear, count-(int) result);
    
  //  System.out.println("Lines with Happy: " + happyAs + ", lines with Sad: " + sadAs+" Lines with Fear: " + fearAs + ", lines with Joy: " + joyAs);
  } 
}
