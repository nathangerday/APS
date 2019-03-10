package aps.ast;
public class AstNum implements IASTExpr {
    Integer val;

    public AstNum(Integer n) {
        val = n;
    }

    @Override
    public String toPrologString() {
        return ("" + val);
    }


    public Value eval(Environment env){
        return new Value(val);
    }
    
}