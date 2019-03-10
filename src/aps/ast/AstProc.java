package aps.ast;
public class AstProc implements IASTDec{
    Ast name;
    AstArgs args;
    AstCmds block;

    public AstProc(Ast name, AstArgs args, AstCmds block){
        this.name = name;
        this.args = args;
        this.block = block;
    }


    public Environment eval(Environment env){
        return null;
    }


    public String toPrologString(){
        return "proc("+name.toPrologString()+","+args.toPrologString()+","+block.toPrologString() + ")";
    }
}