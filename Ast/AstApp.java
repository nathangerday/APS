public class AstApp implements IASTExpr {
    IASTExpr f;
    Ast args;

    AstApp(IASTExpr f, Ast args) {
        this.f = f;
        this.args = args;
    }

    public String toPrologString() {
        return "app(" + f.toPrologString() + "," + args.toPrologString() + ")";
    }


    public Value eval(Environment env){
        return null;
    }
    
}