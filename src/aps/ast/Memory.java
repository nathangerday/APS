package aps.ast;

import java.util.ArrayList;

public class Memory{
    // private Map<Address, Value> memory;
    private ArrayList<Value> memory;

    public Memory(){
        memory = new ArrayList<>();
        // memory = new HashMap<>();
    }

    
    public Address alloc(){
        Address a = new Address();
        this.memory.add(null);
        // this.memory.put(a, null);
        return a;
    }

    public Address allocn(int i){
        Address res;
        if(i > 0){
            res = alloc();
            for(int cpt=1; cpt<i; cpt++){
                alloc();
            }
            return res;
        }else{
            return null;
        }
        
    }

    public boolean mutate(Address a, Value v){
        if(this.memory.size() > a.getId()){
            this.memory.set(a.getId(), v);
            return true;
        }
        return false;
    }

    public Value get(Address a){
        return this.memory.get(a.getId());
    }

    public Value get(int n){
        return this.memory.get(n);
    }
}