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
    public Value evalleftval(Environment env, Memory mem) {
        Value val = lval.evalleftval(env, mem);
        int indice = expr.eval(env, mem).getN();


        if(val.getB() != null){
            return new Value(new Address(val.getB().getAddress().getId() + indice));
        }else{ // On a une addresse
            return new Value(new Address(mem.get(val.getA()).getB().getAddress().getId() + indice));
        }
    }

}