/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class defining Sentence parser
 * @author user
 */
public class SentenceParser extends Parser{
    public SentenceParser(Parser nextParser){
        super(nextParser, "Sentence");
    }

    @Override
    protected Iterable<String> parse(String text) {
        return Arrays.asList(text.split("(?<=(?<![A-Z])(\\. ))"));
    }
}
