public class AstExprs implements Ast {
    Ast expr;
    Ast exprs;

    AstExprs(Ast expr, Ast exprs) {
        this.expr = expr;
        this.exprs = exprs;
    }

    public String toPrologString() {
        return expr.toPrologString() + " " + exprs.toPrologString();
    }
}