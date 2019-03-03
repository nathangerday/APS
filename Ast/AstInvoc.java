public class AstInvoc implements IASTExpr {
    IASTExpr f;
    Ast args;

    AstInvoc(IASTExpr f, Ast args) {
        this.f = f;
        this.args = args;
    }

    public String toPrologString() {
        return "invoc(" + f.toPrologString() + "," + args.toPrologString() + ")";
    }
}