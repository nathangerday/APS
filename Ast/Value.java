public class Value{
    Integer N;
    Closure F;
    ClosureRec FR;

    public Value(Integer N){
        this.N = N;
        this.F = null;
        this.FR = null;
    }

    public Value(Closure F){
        this.N = null;
        this.F = F;
        this.FR = null;
    }

    public Value(ClosureRec FR){
        this.N = null;
        this.F = null;
        this.FR = FR;
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
    

}