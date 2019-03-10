package aps.ast;
public class AstFunRec implements IASTDec {
    
    Ast name;
    Ast type;
    AstArgs args;
    IASTExpr expr;

    public AstFunRec(Ast name, Ast type, AstArgs args, IASTExpr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "funrec("+name.toPrologString()+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }


    public Environment eval(Environment env){
        if(name instanceof AstIdent){
            ClosureRec fr = new ClosureRec(((AstIdent)name).getString(), expr, env, args.getAll());
            Environment newenv = new Environment(env);
            newenv.add(((AstIdent)name).getString(), new Value(fr));
            return newenv;
        }
        return null;
    }
}