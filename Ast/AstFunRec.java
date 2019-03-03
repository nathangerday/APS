public class AstFunRec implements Ast {
    
    Ast name;
    Ast type;
    Ast args;
    Ast expr;

    AstFunRec(Ast name, Ast type, Ast args, Ast expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "funrec("+name.toPrologString()+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }
}