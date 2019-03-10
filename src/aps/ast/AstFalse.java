package aps.ast;
public class AstFalse implements IASTExpr {

    public String toPrologString() {
        return "false";
    }

    public Value eval(Environment env){
        return new Value(0);
    }
}