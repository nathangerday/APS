package aps.ast;
public class AstIdent implements IASTExpr {
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


    public Value eval(Environment env){
        return env.get(name);
    }
}