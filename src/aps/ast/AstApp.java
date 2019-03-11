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


    public Value eval(Environment env, Memory mem){
        Value fun = f.eval(env, mem);
        ArrayList<Value> valOfArgs = args.eval(env, mem);

        if(fun.getF() != null){
            return fun.getF().eval(valOfArgs);
        }else if(fun.getFR() != null){
            return fun.getFR().getClosure(fun).eval(valOfArgs);
        }

        return null;
    }
    
}