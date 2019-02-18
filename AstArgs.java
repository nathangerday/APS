public class AstArgs implements Ast {
    Ast arg;
    Ast args;

    AstArgs(Ast arg, Ast args) {
        this.arg = arg;
        this.args = args;
    }

    public String toPrologString() {
        return "args("+arg.toPrologString()+","+ args.toPrologString()+")";
    }
}