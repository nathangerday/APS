public class AstArg implements Ast {
    Ast type;
    Ast name;

    AstArg(Ast name, Ast type) {
        this.name = name;
        this.type = type;
    }

    public String toPrologString() {
        return "arg("+name.toPrologString()+","+ type.toPrologString()+")";
    }
}