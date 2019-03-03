public class AstEcho implements IASTStat{
    IASTExpr expr;

    public AstEcho(IASTExpr expr){
        this.expr = expr;
    }

    public String toPrologString(){
        return "echo("+expr.toPrologString()+")";
    }
    
}