package aps.ast;
public class AstSet implements IASTStat{
    IASTLval name;
    IASTExpr expr;

    public AstSet(IASTLval name, IASTExpr expr){
        this.name = name;
        this.expr = expr;
    }


    public String toPrologString(){
        return "set("+name.toPrologString()+","+expr.toPrologString()+")";
    }

    public Memory eval(Environment env, Memory mem) {
        MemVal evaluated = expr.eval(env, mem);
        Value v = evaluated.getVal();
        evaluated = name.evalleftval(env, evaluated.getMem());
        Address a = evaluated.getVal().getA();
        Memory toMutate = evaluated.getMem();
        toMutate.mutate(a, v);

        return toMutate;
    }
}