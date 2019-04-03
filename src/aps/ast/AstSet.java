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
        Value v = expr.eval(env, mem);
        Address a = name.evalleftval(env, mem).getA();
        mem.mutate(a, v);

        return mem;
    }
}