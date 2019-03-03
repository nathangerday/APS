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


    public Context eval(Context c){
        int val1 = 0;
        int val2 = 0;

        if(op == Op.ADD){

        }else if(op == Op.SUB){

        }else if(op == Op.MUL){

        }else if(op == Op.SUB){

        }else if(op == Op.AND){
            
        }


        if(a1 instanceof AstNum){
            val1 = ((AstNum)a1).getVal();
        }

        if(a2 instanceof AstNum){
            val2 = ((AstNum)a2).getVal();
        }
        
        System.out.println(val1 + val2);
        return null;
    }
}