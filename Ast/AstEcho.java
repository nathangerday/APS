public class AstEcho implements IASTStat{
    IASTExpr expr;

    public AstEcho(IASTExpr expr){
        this.expr = expr;
    }

    public String toPrologString(){
        return "echo("+expr.toPrologString()+")";
    }

    public OutStream eval(Environment env, OutStream o){
        System.out.println(expr.eval(env).getN());
        return null;
    }
    
}