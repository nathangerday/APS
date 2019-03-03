public class AstFun implements Ast {
    
    Ast name;
    Ast type;
    Ast args;
    Ast expr;

    AstFun(Ast name, Ast type, Ast args, Ast expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "fun("+name.toPrologString()+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }


    public Context eval(Context c){
        return null;
    }
}