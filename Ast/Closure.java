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

    public Value eval(ArrayList<Value> valOfArgs){
        Environment copyEnv = new Environment(env);
        if(args.size() != valOfArgs.size()){
            System.err.println("Different size in function application");
        }

        //TODO Dans quel ordre ajouter les args ? Si on commence par le début, c'est eux qui ont le plus de priorité si plusieurs ont le meme nom
        for(int i = 0; i<args.size(); i++){
            copyEnv.add(args.get(i), valOfArgs.get(i));
        }

        return e.eval(copyEnv);

    }
    
}