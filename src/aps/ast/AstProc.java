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


    public String toPrologString(){
        return "proc("+name.toPrologString()+","+args.toPrologString()+","+block.toPrologString() + ")";
    }

    @Override
    public Context eval(Context con) {
        ProceduralClosure pc = new ProceduralClosure(block, con.getEnv(), args.getAll());
        if(name instanceof AstIdent){
            Environment newenv = new Environment(con.getEnv());
            Memory newmem = new Memory(con.getMem());
            newenv.add(((AstIdent)name).getString(), new Value(pc)); 
            return new Context(newenv, newmem);
        }
        return null;
    }
}