package aps.ast;

public class AstNth implements IASTLval{

    // IASTLval lval;
    IASTLval lval;
    IASTExpr expr;
    
    // public AstNth(IASTLval lval, IASTExpr expr){
    public AstNth(IASTLval lval, IASTExpr expr){
        this.lval = lval;
        this.expr = expr;
    }
    

    @Override
    public String toPrologString() {
        return "nth("+this.lval.toPrologString()+","+this.expr.toPrologString()+")";
    }

    @Override
    public Address evalleftval(Environment env, Memory mem) {
        Address a = lval.evalleftval(env, mem);
        int indice = expr.eval(env, mem).getN();
        return new Address(indice + a.getId());
    }

}