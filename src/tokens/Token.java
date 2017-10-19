/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tokens;

import java.util.function.Predicate;

/**
 * Class defining interface of each token
 * @author Alexander Pykavy
 */
public interface Token {
    /**
     * Print token
     */
    public void print();
    
    /**
     * Count word in token
     * @param word word to find in token
     * @return amount of words
     */
    public int count(String word);
    
    /**
     * Remove token using predicate
     * @param predicate predicate
     * @return is it suitable for predicate
     */
    public boolean remove(Predicate<String> predicate);
}
