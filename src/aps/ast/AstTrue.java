package aps.ast;
public class AstTrue implements IASTExpr {

    public String toPrologString() {
        return "true";
    }


    public MemVal eval(Environment env, Memory mem){
        return new MemVal(mem, new Value(1));
    }
}