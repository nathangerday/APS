public class AstArg implements Ast {
    Ast type;
    String name;

    AstArg(String name, Ast type) {
        this.name = name;
        this.type = type;
    }

    public String toPrologString() {
        return "arg("+name+","+ type.toPrologString()+")";
    }
}