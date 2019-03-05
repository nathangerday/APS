public class AstArg implements Ast {
    Ast type;
    Ast name;

    AstArg(Ast name, Ast type) {
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return ((AstIdent)name).getString();
    }

    public String toPrologString() {
        return "arg("+name.toPrologString()+","+ type.toPrologString()+")";
    }
}