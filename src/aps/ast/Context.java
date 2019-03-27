package aps.ast;

public class Context {
    private Environment env;
    private Memory mem;

    public Context(Environment env, Memory mem){
        this.env = env;
        this.mem = mem;
    }

    public Environment getEnv(){
        return this.env;
    }

    public Memory getMem(){
        return this.mem;
    }
}