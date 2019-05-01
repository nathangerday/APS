package aps.ast;
public class AstIf implements IASTExpr {
    IASTExpr cond;
    IASTExpr then;
    IASTExpr e;

    public AstIf(IASTExpr cond, IASTExpr then, IASTExpr e) {
        this.cond = cond;
        this.then = then;
        this.e = e;
    }

    public String toPrologString() {
        return "if(" + cond.toPrologString() + "," + then.toPrologString() + "," + e.toPrologString() + ")";
    }

    public MemVal eval(Environment env, Memory mem){
        MemVal evaluated = cond.eval(env, mem);
        Integer c = evaluated.getVal().getN();
        if(c == 1){
            return then.eval(env, evaluated.getMem());
        }else if(c == 0){
            return e.eval(env, evaluated.getMem());
        }
        return null;
    }
}