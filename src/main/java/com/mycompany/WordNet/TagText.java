/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.WordNet;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 *
 * @author Danyal-PC
 */
public class TagText {
    
       private static final MaxentTagger tagger = new MaxentTagger("streaming/english-left3words-distsim.tagger");
       public static String tagged;
    
              public static String TaggingToTweetText(String text)
       {
               tagged = tagger.tagString(text);
               return tagged;
       }
              
       public static String cleanText(String tweet)
   {
        String filterText="";

        String Raw = tweet.replaceAll("[^\\p{L}\\p{Z}]","").replaceAll("\n", "").replaceAll("[()?:!.,;{}]+", "");

        String[] result = Raw.split(" ");
        for (int x=0; x<result.length; x++)
        {
            if(result[0].startsWith("?")||result[x].startsWith("?")) {
                result[x]="";
                filterText=filterText+result[x]+" ";
            }
            if(result[x].startsWith("https")||result[x].startsWith("#")||result[x].startsWith(".")||result[x].startsWith("@")||result[x].matches("RT")||result[x].startsWith("_")||result[x].matches("-")||result[x].matches(":"))
              {
                         //  break;
              }
            
            else
              filterText=filterText+result[x]+" ";
        
        }
             return filterText;
    }
       
       
}
