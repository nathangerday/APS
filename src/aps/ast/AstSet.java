package aps.ast;
public class AstSet implements IASTStat{
    Ast name;
    IASTExpr expr;

    public AstSet(Ast name, IASTExpr expr){
        this.name = name;
        this.expr = expr;
    }


    public OutStream eval(Environment env, OutStream o){
        return null;
    }


    public String toPrologString(){
        return "set("+name.toPrologString()+","+expr.toPrologString()+")";
    }
}