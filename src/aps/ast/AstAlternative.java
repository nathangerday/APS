package aps.ast;
public class AstAlternative implements IASTStat{
    IASTExpr cond;
    AstCmds b1;
    AstCmds b2;

    public AstAlternative(IASTExpr cond, AstCmds b1, AstCmds b2){
        this.cond = cond;
        this.b1 = b1;
        this.b2 = b2;
    }


    public String toPrologString(){
        return "alternative("+cond.toPrologString()+","+b1.toPrologString()+","+b2.toPrologString()+")";
    }

    @Override
    public Memory eval(Environment env, Memory mem) {
        MemVal evaluated = cond.eval(env, mem);
        Integer c = evaluated.getVal().getN();
        if(c == 1){
            return b1.eval(env, evaluated.getMem());
        }else if(c == 0){
            return b2.eval(env, evaluated.getMem());
        }
        return null;
    }
}