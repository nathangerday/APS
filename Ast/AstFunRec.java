public class AstFunRec implements IASTDec {
    
    Ast name;
    Ast type;
    Ast args;
    IASTExpr expr;

    AstFunRec(Ast name, Ast type, Ast args, IASTExpr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "funrec("+name.toPrologString()+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }


    public Environment eval(Environment env){
        return null;
    }
}