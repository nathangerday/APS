package aps.ast;
public class AstWhile implements IASTStat{
    IASTExpr cond;
    AstCmds block;

    public AstWhile(IASTExpr cond, AstCmds block){
        this.cond = cond;
        this.block = block;
    }


    public String toPrologString(){
        return "while("+cond.toPrologString()+","+block.toPrologString()+")";
    }

    @Override
    public Memory eval(Environment env, Memory mem) {
        MemVal evaluated = cond.eval(env, mem);
        if(evaluated.getVal().getN() == 0){
            return evaluated.getMem();
        }else if(evaluated.getVal().getN() == 1){
            return this.eval(env, block.eval(env, evaluated.getMem()));
        }
        return null;
    }
}