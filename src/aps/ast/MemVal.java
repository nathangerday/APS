package aps.ast;

public class MemVal{
    private Memory mem;
    private Value val;

    public MemVal(Memory mem, Value val){
        this.mem = mem;
        this.val = val;
    }

    public Memory getMem(){
        return this.mem;
    }

    public Value getVal(){
        return this.val;
    }

}
