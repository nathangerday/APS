public class AstFun implements IASTDec {
    
    Ast name;
    Ast type;
    Ast args;
    IASTExpr expr;

    AstFun(Ast name, Ast type, Ast args, IASTExpr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
        this.args = args;
    }

    public String toPrologString() {
        return "fun("+name.toPrologString()+","+ type.toPrologString()+","+ args.toPrologString() +"," + expr.toPrologString() +")";
    }


    public Environment eval(Environment env){
        return null;
    }
}