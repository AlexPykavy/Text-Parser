/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tokens;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Class defining token with childs
 * @author Alexander Pykavy
 */
public class CompositeToken implements Token{
    private final ArrayList<Token> childs;
    private final String name;
    
    public CompositeToken(String name){
        childs = new ArrayList<>();
        this.name = name;
    }
    
    @Override
    public void print() {
        for (Token child : childs)
            child.print();
        System.out.print(name.equals("Paragraph") ? "\n" : "");
    }

    @Override
    public int count(String word){
        int sum = 0;
        for (Token child : childs)
            sum += child.count(word);
        
        return sum;
    }
    
    @Override
    public boolean remove(Predicate<String> predicate){
        ArrayList<Token> removingChilds = new ArrayList<>();
        
        for (Token child : childs){
            if (child.remove(predicate))
                removingChilds.add(child);
        }
        
        for (Token removeToken : removingChilds)
            removeChild(removeToken);
        
        return false;
    }
    
    /**
     * Add child to token
     * @param child child token
     */
    public void addChild(Token child) {
        childs.add(child);
    }

    /**
     * Remove child from token
     * @param child child token
     */
    public void removeChild(Token child) {
        childs.remove(child);
    }

    /**
     * Get child by index
     * @param index index of child token
     * @return child token
     */
    public Token getChild(int index) {
        return childs.get(index);
    }
    
    /**
     * Get all childs
     * @return sequence of childs
     */
    public Iterable<Token> getChilds(){
        return childs;
    } 
    
    @Override
    public String toString(){
        String result = "";
        for (Token child : childs)
            result += child.toString();
        result += name.equals("Paragraph") ? "\n" : "";

        return result;
    }
}
