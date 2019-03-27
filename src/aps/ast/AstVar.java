package aps.ast;
public class AstVar implements IASTDec{
    Ast name;
    Ast type;

    public AstVar(Ast name, Ast type){
        this.name = name;
        this.type = type;
    }

    public Context eval(Context con){
        if(name instanceof AstIdent){
            Environment newenv = new Environment(con.getEnv());
            Memory newmem = new Memory(con.getMem());

            Address a = newmem.alloc();
            newenv.add(((AstIdent)name).getString(), new Value(a));
            return new Context(newenv, newmem);
        }
        return null;
    }


    public String toPrologString(){
        return "var("+name.toPrologString()+","+type.toPrologString()+")";
    }

}