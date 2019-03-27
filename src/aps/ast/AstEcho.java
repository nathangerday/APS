package aps.ast;
public class AstEcho implements IASTStat{
    IASTExpr expr;

    public AstEcho(IASTExpr expr){
        this.expr = expr;
    }

    public String toPrologString(){
        return "echo("+expr.toPrologString()+")";
    }

    public Memory eval(Environment env, Memory mem) {
        System.out.println(expr.eval(env, mem).getN());
        return mem;
    }
    
}