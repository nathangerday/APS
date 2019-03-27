package aps.ast;

import java.util.HashMap;
import java.util.Map;

public class Memory{
    private Map<Address, Value> memory;

    public Memory(){
        memory = new HashMap<>();
    }

    
    public Address alloc(){
        Address a = new Address();
        this.memory.put(a, null);
        return a;
    }

    public boolean mutate(Address a, Value v){
        if(this.memory.containsKey(a)){
            this.memory.put(a, v);
            return true;
        }
        return false;
    }

    public Value get(Address a){
        return this.memory.get(a);
    }
}