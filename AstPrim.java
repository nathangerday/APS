public class AstPrim implements Ast {
    Op op;
    Ast a1;
    Ast a2;

    AstPrim(Op op, Ast a1, Ast a2) {
        this.op = op;
        this.a1 = a1;
        this.a2 = a2;
    }


    public String toPrologString() {
        if(a2 == null){
            return op.toString() + "(" + a1.toPrologString() + ")";
        }else{
            return op.toString() + "(" + a1.toPrologString() + "," + a2.toPrologString() + ")";
        }
    }
    // public String toPrologString() {
    //     return op.toString() + "(" + a1.toPrologString() + "," + a2.toPrologString() + ")";
    // }
}