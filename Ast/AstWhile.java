public class AstWhile implements IASTStat{
    IASTExpr cond;
    AstCmds block;

    public AstWhile(IASTExpr cond, AstCmds block){
        this.cond = cond;
        this.block = block;
    }


    public OutStream eval(Environment env, OutStream o){
        return null;
    }


    public String toPrologString(){
        return "while("+cond.toPrologString()+","+block.toPrologString()+")";
    }
}