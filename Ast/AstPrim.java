import java.util.ArrayList;


public class AstPrim implements IASTExpr {
    Op op;
    AstExprs exprs;

    AstPrim(Op op, AstExprs e) {
        this.op = op;
        this.exprs = e;
    }


    public String toPrologString() {
        return op.toString() + "(" + exprs.toPrologString() + ")";
    }


    public Value eval(Environment env){
        ArrayList<Value> vals = exprs.eval(env);
        Integer tmpval1;
        Integer tmpval2 = null;
        tmpval1 = vals.get(0).getN();
        if(op != Op.NOT){
            tmpval2 = vals.get(1).getN();
        }
        
        switch(op){
            case ADD:
                return new Value(tmpval1+tmpval2);
            case SUB:
                return new Value(tmpval1-tmpval2);
            case MUL:
                return new Value(tmpval1*tmpval2);
            case DIV:
                return new Value(tmpval1/tmpval2);
            case NOT:
                if(tmpval1 == 1){
                    return new Value(0);
                }else{
                    return new Value(1);
                }
            case AND:
                
                if(tmpval1 == 0){
                    return new Value(0);
                }else{
                    return new Value(tmpval2);
                }
            case OR:
                if(tmpval1 == 1){
                    return new Value(1);
                }else{
                    return new Value(tmpval2);
                }
            case EQ:
                if(tmpval1 == tmpval2){
                    return new Value(1);
                }else{
                    return new Value(0);
                }
            case LT:
                if(tmpval1 < tmpval2){
                    return new Value(1);
                }else{
                    return new Value(0);
                }
        }
        System.err.println("Undefined operation, should never see this message");
        return null;
    }
    
}