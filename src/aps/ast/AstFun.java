package aps.ast;
public class AstFun implements IASTDec {
    
    Ast name;
    Ast type;
    AstArgs args;
    IASTExpr expr;

    public AstFun(Ast name, Ast type, AstArgs args, IASTExpr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "fun("+name.toPrologString()+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }

 
    public Context eval(Context con) {
        Closure c = new Closure(expr, con.getEnv(), args.getAll());
        if(name instanceof AstIdent){
            Environment newenv = new Environment(con.getEnv());
            newenv.add(((AstIdent)name).getString(), new Value(c));
            return new Context(newenv, con.getMem());
        }
        return null;
    }
}