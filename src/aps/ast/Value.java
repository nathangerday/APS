package aps.ast;
public class Value{
    Integer N;
    Closure F;
    ClosureRec FR;
    Address A;
    ProceduralClosure P;
    ProceduralClosureRec PR;
    MemoryBlock B;

    public Value(Integer N){
        this.N = N;
        this.F = null;
        this.FR = null;
        this.A = null;
        this.P = null;
        this.PR = null;
        this.B = null;
    }

    public Value(Closure F){
        this.N = null;
        this.F = F;
        this.FR = null;
        this.A = null;
        this.P = null;
        this.PR = null;
        this.B = null;
    }

    public Value(ClosureRec FR){
        this.N = null;
        this.F = null;
        this.FR = FR;
        this.A = null;
        this.P = null;
        this.PR = null;
        this.B = null;
    }

    public Value(Address A){
        this.N = null;
        this.F = null;
        this.FR = null;
        this.A = A;
        this.P = null;
        this.PR = null;
        this.B = null;
    }

    public Value(ProceduralClosure P){
        this.N = null;
        this.F = null;
        this.FR = null;
        this.A = null;
        this.P = P;
        this.PR = null;
        this.B = null;
    }

    public Value(ProceduralClosureRec PR){
        this.N = null;
        this.F = null;
        this.FR = null;
        this.A = null;
        this.P = null;
        this.PR = PR;
        this.B = null;
    }

    public Value(MemoryBlock B){
        this.N = null;
        this.F = null;
        this.FR = null;
        this.A = null;
        this.P = null;
        this.PR = null;
        this.B = B;
    }
    

    public Integer getN(){
        return this.N;
    }

    public Closure getF(){
        return this.F;
    }

    public ClosureRec getFR(){
        return this.FR;
    }
    
    public Address getA(){
        return this.A;
    }

    public ProceduralClosure getP(){
        return this.P;
    }   
    public ProceduralClosureRec getPR(){
        return this.PR;
    }
    public MemoryBlock getB(){
        return this.B;
    }
}