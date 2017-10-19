/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import tokens.CompositeToken;
import tokens.Token;

/**
 * Class counting words in sentences and text
 * @author Alexander Pykavy
 */
public class Counter {
    private final Map<String, Integer> wordsCounts;
    
    public Counter(String[] words){
        wordsCounts = new HashMap<>();
        
        for (String word : words){
            wordsCounts.put(word, 0);
        }
    }
    
    /**
     * Count words in text and sentences
     * @param text 
     * @return ArrayList of amount of each word in each sentence
     */
    public ArrayList<ArrayList<Integer>> countWordsInEachSentence(CompositeToken text){
        ArrayList<ArrayList<Integer>> sentenceAmounts = new ArrayList<>();
        
        for (Token child : text.getChilds()){
            if (child.getClass() == CompositeToken.class){
                CompositeToken paragraph = (CompositeToken)child;
                for (Token sentence : paragraph.getChilds()){
                    ArrayList<Integer> amounts = new ArrayList<>();
                    sentenceAmounts.add(amounts);
                    
                    for (Entry<String, Integer> entry : wordsCounts.entrySet()){
                        int amount = sentence.count(entry.getKey());
                        amounts.add(amount);
                    }
                }
            }
        }
        
        return sentenceAmounts;
    }
    
    public Map<String, Integer> countWordsInText(CompositeToken text){
        ArrayList<ArrayList<Integer>> countWordsInEachSentence = countWordsInEachSentence(text);
        
        int idxWord = 0;
        for (Entry<String, Integer> entry : wordsCounts.entrySet()){
            int sum = 0;
            for (int idxAmount = 0; idxAmount < countWordsInEachSentence.size(); idxAmount++){
                sum += countWordsInEachSentence.get(idxAmount).get(idxWord);
            }
            entry.setValue(sum);
            idxWord++;
        }
            
        Map<String, Integer> sortedWordsAmounts = wordsCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a.compareTo(b), LinkedHashMap::new));    
        
        return sortedWordsAmounts;
    }
}
