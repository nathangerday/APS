package aps.ast;

import java.util.ArrayList;

public class ProceduralClosure{
    AstCmds block;
    Environment env;
    ArrayList<String> args;

	public ProceduralClosure(AstCmds block, Environment env, ArrayList<String> args) {
        this.block = block;
        this.env = env;
        this.args = args;
	}
    
    public Memory eval(ArrayList<MemVal> valOfArgs, Memory mem){
        Environment copyEnv = new Environment(env);

        if(args.size() != valOfArgs.size()){
            throw new RuntimeException("Different size in function application");
        }


        for(int i = 0; i<args.size(); i++){
            copyEnv.add(args.get(i), valOfArgs.get(i).getVal());
        }

        return block.eval(copyEnv, mem);

    }
}