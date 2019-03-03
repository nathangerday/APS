public class AstInvoc implements Ast {
    Ast f;
    Ast args;

    AstInvoc(Ast f, Ast args) {
        this.f = f;
        this.args = args;
    }

    public String toPrologString() {
        return "invoc(" + f.toPrologString() + "," + args.toPrologString() + ")";
    }
}