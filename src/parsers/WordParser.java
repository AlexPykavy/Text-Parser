/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsers;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Class defining word parser
 * @author Alexander Pykavy
 */
public class WordParser extends Parser{

    public WordParser() {
        super(null, "Word");
    }

    @Override
    protected Iterable<String> parse(String text) {
        ArrayList<String> words = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(text, " .,?!:;'\"$%&", true);
        
        while (st.hasMoreTokens()){
            words.add(st.nextToken());
        }
        
        return words;
    }
}
