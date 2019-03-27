package aps.ast;

public class AstNth implements IASTLval{

    IASTLval lval;
    IASTExpr expr;
    
    public AstNth(IASTLval lval, IASTExpr expr){
        this.lval = lval;
        this.expr = expr;
    }
    

    @Override
    public String toPrologString() {
        return "nth("+this.lval.toPrologString()+","+this.expr.toPrologString()+")";
    }

    @Override
    public Value eval(Environment env, Memory mem) {
        return null;
    }

}