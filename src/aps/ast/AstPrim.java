package aps.ast;
import java.util.ArrayList;


public class AstPrim implements IASTExpr {
    Op op;
    AstExprs exprs;

    public AstPrim(Op op, AstExprs e) {
        this.op = op;
        this.exprs = e;
    }


    public String toPrologString() {
        return op.toString() + "(" + exprs.toPrologString() + ")";
    }


    public Value eval(Environment env, Memory mem){
        ArrayList<Value> vals = exprs.eval(env,mem);
        Value tmpval1;
        Value tmpval2 = null;
        tmpval1 = vals.get(0);
        if(op != Op.NOT && op != Op.LEN && op != Op.ALLOC){ // Unary
            tmpval2 = vals.get(1);
        }
        switch(op){
            case ADD:
                return new Value(tmpval1.getN()+tmpval2.getN());
            case SUB:
                return new Value(tmpval1.getN()-tmpval2.getN());
            case MUL:
                return new Value(tmpval1.getN()*tmpval2.getN());
            case DIV:
                return new Value(tmpval1.getN()/tmpval2.getN());
            case NOT:
                if(tmpval1.getN() == 1){
                    return new Value(0);
                }else{
                    return new Value(1);
                }
            case AND:
                
                if(tmpval1.getN() == 0){
                    return new Value(0);
                }else{
                    return new Value(tmpval2.getN());
                }
            case OR:
                if(tmpval1.getN() == 1){
                    return new Value(1);
                }else{
                    return new Value(tmpval2.getN());
                }
            case EQ:
                if(tmpval1.getN() == tmpval2.getN()){
                    return new Value(1);
                }else{
                    return new Value(0);
                }
            case LT:
                if(tmpval1.getN() < tmpval2.getN()){
                    return new Value(1);
                }else{
                    return new Value(0);
                }
            case ALLOC:
                int nb = tmpval1.getN();
                Address beginMemBlock = mem.allocn(nb);
                return new Value(new MemoryBlock(beginMemBlock, nb));
                
            case NTH:
                MemoryBlock b = tmpval1.getB();
                int indice = tmpval2.getN();
                return mem.get(b.getAddress().getId()+indice);
            case LEN:
                return new Value(tmpval1.getB().getSize());

        }
        System.err.println("Undefined operation, should never see this message");
        return null;
    }
    
}