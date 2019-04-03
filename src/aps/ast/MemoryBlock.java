package aps.ast;

public class MemoryBlock{
    private int size;
    private Address begin;

    public MemoryBlock(Address b, int s){
        this.size = s;
        this.begin = b;
    }

    public int getSize(){
        return this.size;
    }

    public Address getAddress(){
        return this.begin;
    }
}