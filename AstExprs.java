public class AstExprs implements Ast {
    Ast expr;
    Ast exprs;

    AstExprs(Ast expr, Ast exprs) {
        this.expr = expr;
        this.exprs = exprs;
    }

    AstExprs(Ast expr){
        this.expr = expr;
        this.exprs = null;
    }

    public String toPrologString() {
        if(this.exprs != null){
            return "exprs("+expr.toPrologString() + "," + exprs.toPrologString()+")";
        }else{
            return "exprs("+ expr.toPrologString() +",exprs())";
        }
    }
}