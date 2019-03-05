public class AstAbs implements IASTExpr {
    Ast args;
    IASTExpr expr;

    AstAbs(Ast args, IASTExpr expr) {
        this.args = args;
        this.expr = expr;
    }

    public String toPrologString() {
        return "abs(" + args.toPrologString() + "," + expr.toPrologString() + ")";
    }

    public Value eval(Environment env){
        //TODO Eval
        return null;
    }
}