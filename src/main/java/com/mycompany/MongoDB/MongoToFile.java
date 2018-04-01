/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.MongoDB;

import com.mongodb.DBCursor;
import static com.mycompany.List.CountryCodeList.AfricaCodesSet;
import static com.mycompany.List.CountryCodeList.AntarcticaCodesSet;
import static com.mycompany.List.CountryCodeList.AsianCodesSet;
import static com.mycompany.List.CountryCodeList.EuropeanCodesSet;
import static com.mycompany.List.CountryCodeList.NorthAmericacodesSet;
import static com.mycompany.List.CountryCodeList.OceaniacodesSet;
import static com.mycompany.List.CountryCodeList.SouthAmericaCodesSet;

import static com.mycompany.MongoDB.StoreDocument.getTweetMongo;
import static com.mycompany.MongoDB.FileHandling.closeFile;
import static com.mycompany.MongoDB.FileHandling.createFile;
import static com.mycompany.MongoDB.StoreDocument.connectToMongo;
import static com.mycompany.MongoDB.StoreDocument.disconnectToMongo;
import static com.mycompany.MongoDB.StoreDocument.getTweetTextMongo;
import static com.mycompany.MongoDB.StoreDocument.storeCounties;
import static com.mycompany.MongoDB.StoreDocument.storeLanguages;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Danyal-PC
 */
public class MongoToFile {
   
       private static DBCursor cursor;
       private static BufferedWriter bufferedWriter;
               static int count=0;
    
      public static void mongoDBTextToJSON(String TXT_FILE , String table) throws IOException
        { 
            cursor= getTweetTextMongo(table);
            
          try {
                    bufferedWriter = createFile(TXT_FILE);
              while(cursor.hasNext()) 
                 {
                     cursor.next();
                     String language = cursor.curr().get("Language").toString();
                            if(language.equals("en")){
                    bufferedWriter.write(cursor.curr().get("Text").toString());
                    bufferedWriter.newLine();  
                            } 
                  }
              closeFile();
             }catch(Exception e)
              {
                  System.out.println("POSParser Class MongoDB TEXT exception to write data \n");
              }
   }
       
       public static void mongoDBToJSON(String JSON_FILE , String table) throws IOException{
             cursor= getTweetMongo(table);
       try{
            bufferedWriter = createFile(JSON_FILE);
              while(cursor.hasNext()) 
                 {   
                    bufferedWriter.write(cursor.next().toString());
                    bufferedWriter.newLine();
                 }
              closeFile();
          }catch(Exception e)
              {
                  System.out.println("POSParser Class MongoDB Results exception to write data");
              } 
        }
       
      public static void mongoDBRawManipulate(String table) throws IOException
      {
          int lang_english=0;  int lang_Spanish = 0;  int lang_German = 0;  int lang_France = 0; int lang_china=0; 
          int lang_Italian=0;  int lang_Turkish = 0;  int lang_Arabic = 0;  int lang_Japan = 0; int country_pak=0;
          int lang_Undefind=0; int lang_Urdu=0;  int lang_Thailand=0;  int lang_Russian=0; int lang_Portugese=0;
          int continent_Asian=0;  int continent_African=0; int continent_Oceania=0; int continent_NorthAmerica=0;
          int continent_SouthAmerica=0; int continent_European=0; int continent_Antarctica=0;
             
         cursor=  getTweetMongo(table);

          while(cursor.hasNext()) 
            {
                cursor.next();
               
               String location = cursor.curr().get("Location").toString();
                        if(location.equals("Pakistan"))
                            country_pak++;
                        
               String countrycode = cursor.curr().get("CountryCode").toString();
                        if(AsianCodesSet.contains(countrycode))
                              continent_Asian++;   
                   else if(AfricaCodesSet.contains(countrycode))
                              continent_African++;      
                   else if(EuropeanCodesSet.contains(countrycode))
                              continent_European++;     
                   else if(OceaniacodesSet.contains(countrycode))
                              continent_Oceania++;     
                   else if(SouthAmericaCodesSet.contains(countrycode))
                              continent_SouthAmerica++; 
                   else if(NorthAmericacodesSet.contains(countrycode))
                              continent_NorthAmerica++;
                   else if(AntarcticaCodesSet.contains(countrycode))
                              continent_Antarctica++;   
                        
               String language = cursor.curr().get("Language").toString();
                            if(language.equals("en"))
                            lang_english++;
                       else if (language.equals("pt"))
                            lang_Portugese++;
                       else if (language.equals("ru"))
                            lang_Russian++;
                       else if (language.equals("th"))
                            lang_Thailand++;
                       else if (language.equals("ur")||language.equals("hi"))
                            lang_Urdu++;
                       else if(language.equals("ar"))
                            lang_Arabic++;
                       else if(language.equals("ja"))
                            lang_Japan++;   
                       else if(language.equals("es"))
                            lang_Spanish++;  
                       else if(language.equals("it"))
                            lang_Italian++;    
                       else if(language.equals("fr"))
                            lang_France++;    
                       else if(language.equals("de")||language.equals("da")||language.equals("nl"))
                            lang_German++; 
                       else if(language.equals("tr"))
                            lang_Turkish++; 
                       else if (language.equals("und"))
                            lang_Undefind++;
                       else if(language.equals("ko")||language.equals("zh"))
                            lang_china++;        
                }
                          
       try{
                disconnectToMongo();
                connectToMongo("127.0.0.1",27017,"Languages");

              storeLanguages(lang_english,
                             lang_Spanish,
                             lang_German,
                             lang_France,
                             lang_Italian,
                             lang_Turkish,
                             lang_Arabic,
                             lang_Japan,
                             lang_china,
                             lang_Undefind,
                             lang_Urdu,
                             lang_Thailand,
                             lang_Russian,
                             lang_Portugese);
              
                disconnectToMongo();
                connectToMongo("127.0.0.1",27017,"Continent");

              storeCounties(country_pak,
                            continent_Asian,
                            continent_African,
                            continent_Oceania,
                            continent_NorthAmerica,
                            continent_SouthAmerica,
                            continent_European,
                            continent_Antarctica
                             );
              System.out.println(count);
       }catch(Exception e)
              {
                  System.out.println("POSParser Class MongoDB Raw exception to write data");
              } 
        }
       
    
}
