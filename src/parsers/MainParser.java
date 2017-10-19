/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import run.Main;
import tokens.CompositeToken;
import tokens.LeafToken;
import tokens.Token;

/**
 * Class defining main parser
 * @author Alexander Pykavy
 */
public class MainParser {
    Parser wordParser, sentenceParser, paragraphParser;
    
    public MainParser(){
        wordParser = new WordParser();
        sentenceParser = new SentenceParser(wordParser);
        paragraphParser = new ParagraphParser(sentenceParser);
    }
    
    public CompositeToken parse(String path){      
        CompositeToken text = new CompositeToken("Text");
        
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String curLine = br.readLine();
            
            while (curLine != null){
                // If code
                if (curLine.equals("<code>"))
                {
                    String code = curLine;
                    do{
                        curLine = br.readLine();
                        code += "\n" + curLine;
                    } while(!curLine.equals("</code>"));
                    
                    LeafToken codeToken = new LeafToken(code);
                    
                    text.addChild(codeToken);
                }
                // Else text
                else{
                    curLine = prepareText(curLine);
                
                    paragraphParser.parse(text, curLine);
                }
                
                curLine = br.readLine();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return text;
    }
    
    public Iterable<Token> getParagraphs(){
        return paragraphParser.getCurrentLayerTokens();
    }
    
    public Iterable<Token> getSentences(){
        return sentenceParser.getCurrentLayerTokens();
    }
    
    public Iterable<Token> getWords(){
        return wordParser.getCurrentLayerTokens();
    }
    
    private String prepareText(String text){
        text = text.replaceAll("Mr.", "Mr");
        text = text.replaceAll("Mrs.", "Mrs");
        text = text.replaceAll("Dr.", "Dr");
        
        text = text.replaceAll("(\t)+", " ");
        text = text.replaceAll("( )+", " ");
    
        return text;
    }
    
}
