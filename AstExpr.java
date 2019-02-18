public class AstExpr implements Ast {
    Ast expr;

    AstExpr(Ast expr) {
        this.expr = expr;
    }

    public String toPrologString() {
        return expr.toPrologString();
    }
}