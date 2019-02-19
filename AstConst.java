public class AstConst implements Ast {
    
    String name;
    Ast type;
    Ast expr;

    AstConst(String name, Ast type, Ast expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    public String toPrologString() {
        return "const("+name+","+ type.toPrologString()+"," + expr.toPrologString() +")";
    }
}