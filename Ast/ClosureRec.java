import java.util.ArrayList;

public class ClosureRec{
    IASTExpr e;
    Environment env;
    ArrayList<String> args;
    String name;

    public ClosureRec(String name, IASTExpr e, Environment env, ArrayList<String> args){
        this.e = e;
        this.env = env;
        this.args = args;
        this.name = name;
    }

    public Closure getClosure(Value fr){
        Environment newenv = new Environment(env);
        newenv.add(name, fr);
        return new Closure(e, newenv, args);
    }
}