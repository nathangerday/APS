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

    public Environment eval(Environment env){
        if(name instanceof AstIdent){
            Environment newenv = new Environment(env); //Copy the environment, we don't want to change it directly
            newenv.add(((AstIdent)name).getString(), expr.eval(env));
            return newenv;
        }
        return null;
    }
}