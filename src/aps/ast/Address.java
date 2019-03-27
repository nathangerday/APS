package aps.ast;

public class Address{
    private static int counter = 0;
    private int id;
    
    public Address(){
        this.id = counter;
        counter++;
    }

    public int getId(){
        return this.id;
    }
}