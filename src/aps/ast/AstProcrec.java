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


    public String toPrologString(){
        return "procrec("+name.toPrologString()+","+args.toPrologString()+","+block.toPrologString() + ")";
    }

    public Context eval(Context con) {
        if(name instanceof AstIdent) {
            ProceduralClosureRec pcr = new ProceduralClosureRec(((AstIdent)name).getString(), block, con.getEnv(), args.getAll());
            Environment newenv = new Environment(con.getEnv());
            newenv.add(((AstIdent)name).getString(), new Value(pcr));
            return new Context(newenv, con.getMem());
        }
        return null;
    }
}