public class AstIf implements IASTExpr {
    IASTExpr cond;
    IASTExpr then;
    IASTExpr e;

    AstIf(IASTExpr cond, IASTExpr then, IASTExpr e) {
        this.cond = cond;
        this.then = then;
        this.e = e;
    }

    public String toPrologString() {
        return "if(" + cond.toPrologString() + "," + then.toPrologString() + "," + e.toPrologString() + ")";
    }
}