public class AstIf implements Ast {
    Ast cond;
    Ast then;
    Ast e;

    AstIf(Ast cond, Ast then, Ast e) {
        this.cond = cond;
        this.then = then;
        this.e = e;
    }

    public String toPrologString() {
        return "if(" + cond.toPrologString() + "," + then.toPrologString() + "," + e.toPrologString() + ")";
    }


    public Context eval(Context c){
        return null;
    }
}