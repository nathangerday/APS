package aps.ast;
public class AstStar implements Ast {
    Ast type;
    Ast types;

    public AstStar(Ast type, Ast types) {
        this.type = type;
        this.types = types;
    }

    public String toPrologString() {
        return "star(" + type.toPrologString() +"," + types.toPrologString()+ ")";
    }
}