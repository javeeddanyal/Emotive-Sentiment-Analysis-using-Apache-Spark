/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.WordNet;

/**
 *
 * @author Danyal-PC
 */

import static com.mycompany.List.StopwordsList.stopwordsSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Set;

/**
 * Twokenize -- a tokenizer designed for Twitter text in English and some other European languages.
 */
public class Twokenize {
    
    public static void Tokenizations(String filename,
                                       Set<String> Synsets_Happy,
                                       Set<String> Synsets_Sad,
                                       Set<String> Synsets_Fear,
                                       Set<String> Synsets_Joy) throws IOException {
        
        int count_Happy;
        int count_Sad;
        int count_Fear;
        int count_Joy;
        int count;
        
        try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
            String line;
            count_Happy = 0;
            count_Sad = 0;
            count_Fear = 0;
            count_Joy = 0;
            count = 0;
            
            while ( (line = input.readLine()) != null)
            {
                count++;
                
                String[] toks = line.split("\\s");
                
                for (int i=0; i<toks.length; i++) {
                     if(stopwordsSet.contains(toks[i]))
                           continue; 
                    else if (Synsets_Happy.contains(toks[i]))
                            count_Happy++;
   
                    else if (Synsets_Sad.contains(toks[i]))
                            count_Sad++;
 
                    else if (Synsets_Fear.contains(toks[i]))
                            count_Fear++;

                    else if (Synsets_Joy.contains(toks[i]))
                            count_Joy++;
                }
            }
        }
        
    float result= count_Happy + count_Sad + count_Fear + count_Joy;
        float happy = (count_Happy / result) * 100;
       
        float sad = (count_Sad / result) * 100;
        float fear = (count_Fear / result) *100;
        float joy = (count_Joy / result) * 100;
        
        com.mycompany.MongoDB.StoreDocument.connectToMongo("127.0.0.1",27017,"Results");
        com.mycompany.MongoDB.StoreDocument.storeResults(count, happy, sad, joy, fear, count-(int) result);

    }
    
}
