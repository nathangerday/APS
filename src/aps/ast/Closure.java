package aps.ast;
import java.util.ArrayList;

public class Closure{
    IASTExpr e;
    Environment env;
    ArrayList<String> args;

    public Closure(IASTExpr e, Environment env, ArrayList<String> args){
        this.e = e;
        this.env = env;
        this.args = args;
    }

    public MemVal eval(ArrayList<MemVal> valOfArgs, Memory mem){
        Environment copyEnv = new Environment(env);
        if(args.size() != valOfArgs.size()){
            throw new RuntimeException("Different size in function application");
        }

        for(int i = 0; i<args.size(); i++){
            copyEnv.add(args.get(i), valOfArgs.get(i).getVal());
        }

        return e.eval(copyEnv, mem);

    }
    
}