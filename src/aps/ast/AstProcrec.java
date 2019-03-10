package aps.ast;
public class AstProcrec implements IASTDec{
    Ast name;
    AstArgs args;
    AstCmds block;

    public AstProcrec(Ast name, AstArgs args, AstCmds block){
        this.name = name;
        this.args = args;
        this.block = block;
    }


    public Environment eval(Environment env){
        return null;
    }


    public String toPrologString(){
        return "procrec("+name.toPrologString()+","+args.toPrologString()+","+block.toPrologString() + ")";
    }
}