public class AstId implements Ast {
    String name;

    AstId(String x) {
        name = x;
    }

    @Override
    public String toPrologString() {
        return "\"" + name + "\"";
    }
}