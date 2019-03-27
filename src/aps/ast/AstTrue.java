package aps.ast;
public class AstTrue implements IASTExpr {

    public String toPrologString() {
        return "true";
    }


    public Value eval(Environment env, Memory mem){
        return new Value(1);
    }
}