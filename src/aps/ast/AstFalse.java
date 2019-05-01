package aps.ast;
public class AstFalse implements IASTExpr {

    public String toPrologString() {
        return "false";
    }

    public MemVal eval(Environment env, Memory mem){
        return new MemVal(mem, new Value(0));
    }
}