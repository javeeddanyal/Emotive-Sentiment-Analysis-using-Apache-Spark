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
import edu.smu.tspell.wordnet.*;
import java.util.ArrayList;

/**
 * Displays word forms and definitions for synsets containing the word form
 * specified on the command line. To use this application, specify the word
 * form that you wish to view synsets for, as in the following example which
 * displays all synsets containing the word form "airplane":
 * <br>
 * java TestJAWS airplane
 */
public class TestJAWS
{

	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	public static ArrayList<String> DataBaseSynsets(String wordForm)
	{
            System.setProperty("wordnet.database.dir", "C:/Java/WNdb-3.0/dict/");

                        ArrayList list=new ArrayList();

                        list.add(wordForm);
			//  Get the synsets containing the wrod form
			WordNetDatabase database = WordNetDatabase.getFileInstance();
			Synset[] synsets = database.getSynsets(wordForm);
			//  Display the word forms and definitions for synsets retrieved
			if (synsets.length > 0)
			{
				for (int i = 0; i < synsets.length; i++)
				{
					String[] wordForms = synsets[i].getWordForms();
					for (int j = 0; j < wordForms.length; j++)
					{

                                            if(!wordForms[j].equals(wordForm))
                                                list.add(wordForms[j]);
					}
				
				}
			}
                        
                    return list;
	}
}