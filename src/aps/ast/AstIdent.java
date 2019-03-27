package aps.ast;
public class AstIdent implements IASTExpr, IASTLval {
    String name;

    public AstIdent(String x) {
        name = x;
    }

    public String getString(){
        return this.name;
    }
    

    @Override
    public String toPrologString() {
        return "\"" + name + "\"";
    }


    public Value eval(Environment env, Memory mem){
        Address a;
        if((a = env.get(name).getA()) != null){
            Value v = mem.get(a);
            if(v != null && v.getN() != null){
                return v;
            }

            //If not initialized or not an N, error
            throw new RuntimeException("Error, bad value in : "+this.name);
        }

        return env.get(name);
    }
}