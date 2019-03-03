public class AstIdent implements Ast {
    String name;

    AstIdent(String x) {
        name = x;
    }

    @Override
    public String toPrologString() {
        return "\"" + name + "\"";
    }
}