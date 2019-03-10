package aps.ast;
public class AstAlternative implements IASTStat{
    IASTExpr cond;
    AstCmds b1;
    AstCmds b2;

    public AstAlternative(IASTExpr cond, AstCmds b1, AstCmds b2){
        this.cond = cond;
        this.b1 = b1;
        this.b2 = b2;
    }

    public OutStream eval(Environment env, OutStream o){
        return null;
    }

    public String toPrologString(){
        return "alternative("+cond.toPrologString()+","+b1.toPrologString()+","+b2.toPrologString()+")";
    }
}