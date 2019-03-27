package aps.ast;
import java.util.HashMap;

public class Environment{
    HashMap<String, Value> assoc;

    public Environment(){
        this.assoc = new HashMap<>();
    }
    
    public Environment(Environment e){
        this.assoc = new HashMap<>(e.assoc);
    }

    public void add(String ident, Value val){
        this.assoc.put(ident, val);
    }

    public Value get(String ident){
        return this.assoc.get(ident);
    }

}