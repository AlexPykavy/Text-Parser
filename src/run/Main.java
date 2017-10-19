package run;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import logic.Counter;
import logic.Remover;
import parsers.MainParser;
import tokens.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Main class
 * @author Alexander
 */

public class Main {

    public static void main(String[] args){
        MainParser mainParser = new MainParser();
        CompositeToken text = mainParser.parse("text.txt");

        String[] words = {"the", "that", "do", "take"};
        
        Counter counter = new Counter(words);
        
        System.out.println();
        text.print();
        System.out.println();

        ArrayList<ArrayList<Integer>> countWordsInEachSentence = counter.countWordsInEachSentence(text);
        
        // Print sentences
        int idxSent = 0;
        
        for (Token sentence : mainParser.getSentences()){
            sentence.print();
                    
            int idxWord = 0;
            System.out.print(" - ");
            for (Integer amount : countWordsInEachSentence.get(idxSent))
                System.out.printf("%s: %d ", words[idxWord++], amount);
            System.out.println();
                    
            idxSent++;
        }
   
        System.out.println();
        
        Map<String, Integer> countWordsInText = counter.countWordsInText(text);
        
        // Print all words sorted by amount in the text
        for (Entry<String, Integer> entry : countWordsInText.entrySet())
            System.out.printf("%s - %d time(s)\n", entry.getKey(), entry.getValue());
        
        // Delete from text words starting with consonant
        Remover remover = new Remover();
        remover.removeWordsFromText(text, 3);
        System.out.println();
        
        // Print final text
        text.print();
    }
}
