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
        Integer c = cond.eval(env, mem).getN();
        if(c == 0){
            return mem;
        }else if(c == 1){
            return this.eval(env, block.eval(env, mem));
        }
        return null;
    }
}