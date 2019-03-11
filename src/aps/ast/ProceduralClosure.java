package aps.ast;

import java.util.ArrayList;

public class ProceduralClosure{
    AstCmds block;
    Environment env;
    Memory mem;
    ArrayList<String> args;

	public ProceduralClosure(AstCmds block, Environment env, Memory mem, ArrayList<String> args) {
        this.block = block;
        this.env = env;
        this.mem = mem;
        this.args = args;
	}
    
    public Memory eval(ArrayList<Value> valOfArgs){
        Environment copyEnv = new Environment(env);
        Memory copyMem = new Memory(mem);

        if(args.size() != valOfArgs.size()){
            throw new RuntimeException("Different size in function application");
        }


        for(int i = 0; i<args.size(); i++){
            copyEnv.add(args.get(i), valOfArgs.get(i));
        }

        return block.eval(copyEnv, copyMem);

    }
}