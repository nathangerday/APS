public class AstCall implements IASTStat{
    Ast name;
    AstExprs exprs;

    public AstCall(Ast name, AstExprs exprs){
        this.name = name;
        this.exprs = exprs;
    }


    public OutStream eval(Environment env, OutStream o){
        return null;
    }


    public String toPrologString(){
        return "call("+name.toPrologString()+","+exprs.toPrologString()+")";
    }
}