public class AstFun implements Ast {
    
    String name;
    Ast type;
    Ast args;
    Ast expr;

    AstFun(String name, Ast type, Ast args, Ast expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "fun("+name+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }
}