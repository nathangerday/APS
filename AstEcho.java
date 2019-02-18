public class AstEcho implements Ast{
    Ast expr;

    public AstEcho(Ast expr){
        this.expr = expr;
    }

    public String toPrologString(){
        return "Echo("+expr.toPrologString()+")";
    }
    
}