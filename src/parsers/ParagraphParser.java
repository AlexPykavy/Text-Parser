/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsers;

import java.util.Arrays;

/**
 * Class defining paragraph parser
 * @author Alexander Pykavy
 */
public class ParagraphParser extends Parser{

    public ParagraphParser(Parser nextParser) {
        super(nextParser, "Paragraph");
    }

    @Override
    protected Iterable<String> parse(String text) {
        return Arrays.asList(text);
    }
}
