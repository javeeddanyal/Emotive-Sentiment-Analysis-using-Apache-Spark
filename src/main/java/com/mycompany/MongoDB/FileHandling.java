package com.mycompany.MongoDB;

import java.io.IOException;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandling {
    
       private static FileWriter write;
       private static BufferedWriter bufferedWriter;

       public static BufferedWriter createFile(String file)
       {
           try {
               write = new FileWriter(file);
               bufferedWriter = new BufferedWriter(write);
           } catch (IOException ex) {
               System.out.println("Create file Error");
               Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
           }
           return bufferedWriter;
       }
       
       public static void closeFile()
       {
           try {
              bufferedWriter.close();
               write.close();
           } catch (IOException ex) {
               System.out.println("Close file Error");
               Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
     
   }