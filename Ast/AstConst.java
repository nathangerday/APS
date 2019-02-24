public class AstConst implements Ast {
    
    Ast name;
    Ast type;
    Ast expr;

    AstConst(Ast name, Ast type, Ast expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    public String toPrologString() {
        return "const("+name.toPrologString()+","+ type.toPrologString()+"," + expr.toPrologString() +")";
    }
}