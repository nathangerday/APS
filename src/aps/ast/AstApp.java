package aps.ast;
import java.util.ArrayList;

public class AstApp implements IASTExpr {
    IASTExpr f;
    AstExprs args;

    public AstApp(IASTExpr f, AstExprs args) {
        this.f = f;
        this.args = args;
    }

    public String toPrologString() {
        return "app(" + f.toPrologString() + "," + args.toPrologString() + ")";
    }


    public MemVal eval(Environment env, Memory mem){
        MemVal fun = f.eval(env, mem);
        ArrayList<MemVal> valOfArgs = args.eval(env, fun.getMem());

        if(fun.getVal().getF() != null){
            return fun.getVal().getF().eval(valOfArgs, valOfArgs.get(valOfArgs.size() - 1).getMem());
        }else if(fun.getVal().getFR() != null){
            return fun.getVal().getFR().getClosure(fun.getVal()).eval(valOfArgs, valOfArgs.get(valOfArgs.size() - 1).getMem());
        }

        return null;
    }
    
}