package com.mycompany.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import java.util.Set;
import java.util.StringTokenizer;


public class StopwordsList {
    
	private static final String[] stopwords = {"a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can", "did", "do", "does", "doing", "don", "down", "during", "each", "few", "for", "from", "further", "get", "had", "has", "have", "having", "he", "her", "here", "hers", "herself", "him", "himself", "his", "how", "i", "if", "im", "i'm", "in", "into", "is", "it", "its", "itself", "just", "me", "more", "most", "my", "myself", "no", "nor", "not", "now", "of", "off", "on", "once", "only", "or", "other", "our", "ours", "ourselves", "out", "over", "own", "rt", "s", "same", "she", "should", "so", "some", "such", "t", "than", "that", "the", "their", "theirs", "them", "themselves", "then", "there", "these", "they", "this", "those", "through", "to", "too", "under", "until", "up", "us", "very", "was", "we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "you", "your", "yours", "yourself", "yourselves"};
        public static final Set<String> stopwordsSet = new HashSet<String>(Arrays.asList(stopwords));

        public static ArrayList<String> tags = new ArrayList<String>();
        public static String TEXT;
        
        public static void FetchHashTags(String text)
        {
            StringTokenizer ST = new StringTokenizer(text);
             while (ST.hasMoreTokens()) {
                 if(ST.nextToken().startsWith("#"))
                 { tags.add(ST.nextToken());
                   System.out.println(ST.nextToken()); 
                 }
             }  
       
        }

}

