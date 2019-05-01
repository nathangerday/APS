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
    public MemVal evalleftval(Environment env, Memory mem) {
        MemVal evaluated = lval.evalleftval(env, mem);
        Value val = evaluated.getVal();
        evaluated = expr.eval(env, evaluated.getMem());
        int indice = evaluated.getVal().getN();

        if(val.getB() != null){
            return new MemVal(evaluated.getMem(), new Value(new Address(val.getB().getAddress().getId() + indice)));
        }else{ // On a une addresse
            return new MemVal(evaluated.getMem(), new Value(new Address(evaluated.getMem().get(val.getA()).getB().getAddress().getId() + indice)));
        }
    }

}