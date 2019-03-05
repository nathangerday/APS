public class Value{
    Integer N;
    Closure F;

    public Value(Integer N){
        this.N = N;
        this.F = null;
    }

    public Value(Closure F){
        this.N = null;
        this.F = F;
    }

    public Integer getN(){
        return this.N;
    }

    public Closure getF(){
        return this.F;
    }

}