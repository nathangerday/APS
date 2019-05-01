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


    public MemVal eval(Environment env, Memory mem){
        ArrayList<MemVal> vals = exprs.eval(env,mem);
        MemVal tmpval1;
        MemVal tmpval2 = null;
        tmpval1 = vals.get(0);
        if(op != Op.NOT && op != Op.LEN && op != Op.ALLOC){ // Unary
            tmpval2 = vals.get(1);
        }
        switch(op){
            case ADD:
                return new MemVal(tmpval2.getMem(),  new Value(tmpval1.getVal().getN()+tmpval2.getVal().getN()));
            case SUB:
                return new MemVal(tmpval2.getMem(),  new Value(tmpval1.getVal().getN()-tmpval2.getVal().getN()));
            case MUL:
                return new MemVal(tmpval2.getMem(),  new Value(tmpval1.getVal().getN()*tmpval2.getVal().getN()));
            case DIV:
                return new MemVal(tmpval2.getMem(),  new Value(tmpval1.getVal().getN()/tmpval2.getVal().getN()));
            case NOT:
                if(tmpval1.getVal().getN() == 1){
                    return new MemVal(tmpval1.getMem(), new Value(0));
                }else{
                    return new MemVal(tmpval1.getMem(), new Value(1));
                }
            case AND:
                
                if(tmpval1.getVal().getN() == 0){
                    return new MemVal(tmpval2.getMem(), new Value(0));
                }else{
                    return new MemVal(tmpval2.getMem(), new Value(tmpval2.getVal().getN()));
                }
            case OR:
                if(tmpval1.getVal().getN() == 1){
                    return new MemVal(tmpval2.getMem(), new Value(1));
                }else{
                    return new MemVal(tmpval2.getMem(), new Value(tmpval2.getVal().getN()));
                }
            case EQ:
                if(tmpval1.getVal().getN() == tmpval2.getVal().getN()){
                    return new MemVal(tmpval2.getMem(), new Value(1));
                }else{
                    return new MemVal(tmpval2.getMem(), new Value(0));
                }
            case LT:
                if(tmpval1.getVal().getN() < tmpval2.getVal().getN()){
                    return new MemVal(tmpval2.getMem(), new Value(1));
                }else{
                    return new MemVal(tmpval2.getMem(), new Value(0));
                }
            case ALLOC:
                int nb = tmpval1.getVal().getN();
                Memory toAllocateTo = tmpval1.getMem();
                Address beginMemBlock = toAllocateTo.allocn(nb);
                return new MemVal(toAllocateTo, new Value(new MemoryBlock(beginMemBlock, nb)));
                
            case NTH:
                MemoryBlock b = tmpval1.getVal().getB();
                int indice = tmpval2.getVal().getN();
                return new MemVal(tmpval2.getMem(), tmpval2.getMem().get(b.getAddress().getId()+indice));
            case LEN:
                return new MemVal(tmpval1.getMem(), new Value(tmpval1.getVal().getB().getSize()));

        }
        System.err.println("Undefined operation, should never see this message");
        return null;
    }
    
}