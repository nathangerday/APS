public class AstCmds implements IASTCmds{
    Ast a1;
    AstCmds a2;

    public AstCmds(Ast a1, AstCmds a2){
        this.a1 = a1;
        this.a2 = a2;
    }

    public AstCmds(Ast a){
        this.a1 = a;
        this.a2 = null;
    }

    public String toPrologString(){
        if(this.a2 != null){
            return "cmds(" + a1.toPrologString() + "," + a2.toPrologString() + ")";
        }else{
            return "cmds(" + a1.toPrologString() + ",cmds())";
        }
    }

    public OutStream eval(Environment env, OutStream o){
        if(a1 instanceof IASTStat){
            ((IASTStat)a1).eval(env, o);
        }
        return null;
    }

}