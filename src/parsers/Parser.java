/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsers;

import java.util.ArrayList;
import java.util.List;
import tokens.CompositeToken;
import tokens.LeafToken;
import tokens.Token;

/**
 * Abstract class define all parsers
 * @author Alexander Pykavy
 */
public abstract class Parser {
    protected Parser nextParser;
    protected String name;
    private List<Token> curLayerTokens;
    
    public Parser(Parser nextParser, String name){
        this.nextParser = nextParser;
        this.name = name;
        this.curLayerTokens = new ArrayList<>();
    }

    /**
     * Template method to split text on tokens
     * @param text original text
     * @return sequence of strings
     */
    protected abstract Iterable<String> parse(String text);
    
    /**
     * Method to parse string and add them to parent
     * @param parentToken parent token
     * @param text original text
     */
    public void parse(CompositeToken parentToken, String text){
        Iterable<String> parts = parse(text);
        
        for (String part : parts){
            if (nextParser == null){
                LeafToken leafToken = new LeafToken(part);
                parentToken.addChild(leafToken);
                curLayerTokens.add(leafToken);
            }
            else{
                CompositeToken compositeToken = new CompositeToken(name);
                nextParser.parse(compositeToken, part);
                parentToken.addChild(compositeToken);
                curLayerTokens.add(compositeToken);
            }
        }
    }
    
    /**
     * Get all tokens of one layer
     * @return sequence of tokens
     */
    public Iterable<Token> getCurrentLayerTokens(){
        return curLayerTokens;
    }
}
