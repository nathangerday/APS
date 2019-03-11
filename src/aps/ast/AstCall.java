package aps.ast;

import java.util.ArrayList;

public class AstCall implements IASTStat {
    Ast name;
    AstExprs args;

    public AstCall(Ast name, AstExprs args){
        this.name = name;
        this.args = args;
    }

    public String toPrologString(){
        return "call("+name.toPrologString()+","+args.toPrologString()+")";
    }

    public Memory eval(Environment env, Memory mem) {
        if(name instanceof AstIdent){
            Value proc = env.get(((AstIdent)name).getString());
            ArrayList<Value> valOfArgs = args.eval(env, mem);

            if(proc.getP() != null){
                return proc.getP().eval(valOfArgs);
            }else if(proc.getPR() != null){
                return proc.getPR().getProceduralClosure(proc).eval(valOfArgs);
            }
        }
        return null;
    }
}