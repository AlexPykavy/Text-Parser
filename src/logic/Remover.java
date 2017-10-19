/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import tokens.CompositeToken;

/**
 * Class that remove from text words starting with consonant and fixed size
 * @author Alexander Pykavy
 */
public class Remover {
    
    /**
     * remove from text words starting with consonant and fixed size
     * @param text token of text
     * @param wordLength word length
     */
    public void removeWordsFromText(CompositeToken text, int wordLength){
        text.remove((s) -> {
           if (s.length() != wordLength)
               return false;
           
           return s.substring(0, 1).matches("[a-zA-Z&&[^aeiouAEIOU]]");
        });
    }
}
