package aps.ast;
public class AstConst implements IASTDec {
    
    Ast name;
    Ast type;
    IASTExpr expr;

    public AstConst(Ast name, Ast type, IASTExpr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    public String toPrologString() {
        return "const("+name.toPrologString()+","+ type.toPrologString()+"," + expr.toPrologString() +")";
    }

    public Context eval(Context con) {
        if(name instanceof AstIdent){
            Environment newenv = new Environment(con.getEnv()); //Copy the environment, we don't want to change it directly
            MemVal evaluated = expr.eval(con.getEnv(), con.getMem());
            newenv.add(((AstIdent)name).getString(), evaluated.getVal());
            return new Context(newenv, evaluated.getMem());
        }
        return null;
    }
}