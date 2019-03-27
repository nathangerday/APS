package aps.ast;
public class AstFalse implements IASTExpr {

    public String toPrologString() {
        return "false";
    }

    public Value eval(Environment env, Memory mem){
        return new Value(0);
    }
}