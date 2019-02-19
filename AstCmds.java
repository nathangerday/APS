public class AstCmds implements Ast{
    Ast a1;
    Ast a2;

    public AstCmds(Ast a1, Ast a2){
        this.a1 = a1;
        this.a2 = a2;
    }

    public String toPrologString(){
        return "cmds(" + a1.toPrologString() + "," + a2.toPrologString() + ")";
    }

}