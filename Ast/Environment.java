import java.util.HashMap;

public class Environment{
    HashMap<String, Value> assoc = new HashMap<>();

    public void add(String ident, Value val){
        this.assoc.put(ident, val);
    }

    public Value get(String ident){
        return this.assoc.get(ident);
    }

}