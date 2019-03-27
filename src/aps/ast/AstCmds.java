package aps.ast;
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


    public Memory eval(Environment env, Memory mem) {
        
        if(a1 instanceof IASTStat){
            mem = ((IASTStat)a1).eval(env, mem);
        }else if(a1 instanceof IASTDec){
            Context con = ((IASTDec)a1).eval(new Context(env, mem));
            env = con.getEnv();
            mem = con.getMem();
        }
        if(a2 != null){
            return a2.eval(env, mem);
        }else{
            return mem;
        }
    }

}