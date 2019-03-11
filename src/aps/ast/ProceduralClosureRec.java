package aps.ast;

import java.util.ArrayList;

public class ProceduralClosureRec {
    AstCmds block;
    Environment env;
    ArrayList<String> args;
    String name;

	public ProceduralClosureRec(String name, AstCmds block, Environment env, ArrayList<String> args) {
        this.name = name;
        this.block = block;
        this.env = env;
        this.args = args;
    }
    
    public ProceduralClosure getProceduralClosure(Value pr){
        Environment newenv = new Environment(env);
        newenv.add(name, pr);
        return new ProceduralClosure(block, newenv, args);
    }
}