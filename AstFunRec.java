public class AstFunRec implements Ast {
    
    String name;
    Ast type;
    Ast args;
    Ast expr;

    AstFunRec(String name, Ast type, Ast args, Ast expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "FunRec("+name+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }
}