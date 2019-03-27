package aps.ast;
import java.util.ArrayList;

public class OutStream{
    ArrayList<Integer> values;
    
    public OutStream(){
        this.values = new ArrayList<>();
    }

    public OutStream(OutStream o){
        this.values = new ArrayList<>(o.values); 
    }

    public void add(Integer v){
        values.add(v);
    }

    public void print(){
        for(Integer i : values){
            System.out.println(i);
        }
    }
}