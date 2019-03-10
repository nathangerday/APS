package aps.ast;
public class AstArrow implements Ast {
    Ast type;
    Ast types;

    public AstArrow(Ast types, Ast type) {
        this.type = type;
        this.types = types;
    }

    public String toPrologString() {
        return "arrow(" + types.toPrologString() + "," + type.toPrologString() + ")";
    }
}