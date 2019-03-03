public class AstBlock implements IASTExpr {
    Ast args;
    IASTExpr expr;

    AstBlock(Ast args, IASTExpr expr) {
        this.args = args;
        this.expr = expr;
    }

    public String toPrologString() {
        return "block(" + args.toPrologString() + "," + expr.toPrologString() + ")";
    }

    public Value eval(Environment env){
        return null;
    }
}