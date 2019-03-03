public class AstBlock implements Ast {
    Ast args;
    Ast expr;

    AstBlock(Ast args, Ast expr) {
        this.args = args;
        this.expr = expr;
    }

    public String toPrologString() {
        return "block(" + args.toPrologString() + "," + expr.toPrologString() + ")";
    }
}