/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tokens;

import java.util.function.Predicate;

/**
 * Class defining token without childs
 * @author Alexander Pykavy
 */
public class LeafToken implements Token{
    private final String value; 
    
    public LeafToken(String value){
        this.value = value;
    }
    
    @Override
    public void print(){
        System.out.print(value);
    }

    @Override
    public int count(String word){
        return value.equalsIgnoreCase(word) ? 1 : 0;
    }
    
    @Override
    public boolean remove(Predicate<String> predicate){ 
        return predicate.test(value);
    }
    
    @Override
    public String toString(){
        return value;
    }
    
    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        
        if (o == null)
            return false;
        
        if (o.getClass() != getClass())
            return false;
        
        LeafToken leafToken = (LeafToken)o;
        
        return value.equals(leafToken.value);
    }
}
