package aps.ast;
import java.util.ArrayList;

public class ClosureRec{
    IASTExpr e;
    Environment env;
    Memory mem;
    ArrayList<String> args;
    String name;

    public ClosureRec(String name, IASTExpr e, Environment env, Memory mem, ArrayList<String> args){
        this.e = e;
        this.env = env;
        this.mem = mem;
        this.args = args;
        this.name = name;
    }

    public Closure getClosure(Value fr){
        Environment newenv = new Environment(env);
        newenv.add(name, fr);
        return new Closure(e, newenv, mem, args);
    }
}