public class AstEcho implements IASTStat{
    IASTExpr expr;

    public AstEcho(IASTExpr expr){
        this.expr = expr;
    }

    public String toPrologString(){
        return "echo("+expr.toPrologString()+")";
    }

    public OutStream eval(Environment env, OutStream o){
        OutStream newo = new OutStream(o);
        o.add(expr.eval(env).getN());
        return o;
    }
    
}