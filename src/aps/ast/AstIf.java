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

    public Value eval(Environment env, Memory mem){
        Integer c = cond.eval(env, mem).getN();
        if(c == 1){
            return then.eval(env, mem);
        }else if(c == 0){
            return e.eval(env, mem);
        }
        return null;
    }
}