import java.util.ArrayList;

public class AstApp implements IASTExpr {
    IASTExpr f;
    AstExprs args;

    AstApp(IASTExpr f, AstExprs args) {
        this.f = f;
        this.args = args;
    }

    public String toPrologString() {
        return "app(" + f.toPrologString() + "," + args.toPrologString() + ")";
    }


    public Value eval(Environment env){
        Value fun = f.eval(env);
        ArrayList<Value> valOfArgs = args.eval(env);

        if(fun.getF() != null){
            return fun.getF().eval(valOfArgs);
        } 

        return null;
    }
    
}