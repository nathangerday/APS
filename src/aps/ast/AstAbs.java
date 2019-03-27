package aps.ast;
public class AstAbs implements IASTExpr {
    AstArgs args;
    IASTExpr expr;

    public AstAbs(AstArgs args, IASTExpr expr) {
        this.args = args;
        this.expr = expr;
    }

    public String toPrologString() {
        return "abs(" + args.toPrologString() + "," + expr.toPrologString() + ")";
    }

    public Value eval(Environment env, Memory mem){
        Closure c = new Closure(expr, env, mem, args.getAll());
        return new Value(c);
    }
}