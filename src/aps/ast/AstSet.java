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
        //TODO Change for APS2
        // if(name instanceof AstIdent){
        //     Address a;
        //     if((a = env.get(((AstIdent)name).getString()).getA()) != null){
        //         Memory newmem = new Memory(mem);
        //         newmem.mutate(a, expr.eval(env, mem));
        //         return newmem;
        //     }
        // }

        return null;
    }
}