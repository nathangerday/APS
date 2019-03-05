public class AstPrim implements IASTExpr {
    Op op;
    IASTExpr a1;
    IASTExpr a2;

    AstPrim(Op op, IASTExpr a1, IASTExpr a2) {
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


    public Value eval(Environment env){
        Integer val1 = a1.eval(env).getN();
        Integer val2 = null;
        if(a2 != null){
            val2 = a2.eval(env).getN();
        }
        switch(op){
            case ADD:
                return new Value(val1+val2);
            case SUB:
                return new Value(val1-val2);
            case MUL:
                return new Value(val1*val2);
            case DIV:
                return new Value(val1/val2);
            case NOT:
                return new Value(1);
            case AND:
                return new Value(1);
            case OR:
                return new Value(1);
            case EQ:
                return new Value(1);
            case LT:
                return new Value(1);
        }
        return null;
    }
    
}