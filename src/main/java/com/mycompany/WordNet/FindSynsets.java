/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.WordNet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Danyal-PC
 */
public class FindSynsets {
    
    public static Set<String> Synsets_Sad;
    public static Set<String> Synsets_Fear;
    public static Set<String> Synsets_Joy;
    public static Set<String> Synsets_Happy;
    
    public static void searchWord(String happy , String sad , String fear , String joy)
    {
             Synsets_Happy = new HashSet<String>(TestJAWS.DataBaseSynsets(happy.toLowerCase()));
        
             //Synsets_Happy = TestJAWS.DataBaseSynsets(happy.toLowerCase());
               
             Synsets_Sad = new HashSet<String>(TestJAWS.DataBaseSynsets(sad.toLowerCase()));
     
             Synsets_Fear = new HashSet<String>(TestJAWS.DataBaseSynsets(fear.toLowerCase()));
             
             Synsets_Joy = new HashSet<String>(TestJAWS.DataBaseSynsets(joy.toLowerCase()));
             
    }
    
}
