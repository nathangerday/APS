public class AstAbs implements IASTExpr {
    AstArgs args;
    IASTExpr expr;

    AstAbs(AstArgs args, IASTExpr expr) {
        this.args = args;
        this.expr = expr;
    }

    public String toPrologString() {
        return "abs(" + args.toPrologString() + "," + expr.toPrologString() + ")";
    }

    public Value eval(Environment env){
        Closure c = new Closure(expr, env, args.getAll());
        return new Value(c);
    }
}