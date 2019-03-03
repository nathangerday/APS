public class AstConst implements IASTDec {
    
    Ast name;
    Ast type;
    IASTExpr expr;

    AstConst(Ast name, Ast type, IASTExpr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    public String toPrologString() {
        return "const("+name.toPrologString()+","+ type.toPrologString()+"," + expr.toPrologString() +")";
    }
}