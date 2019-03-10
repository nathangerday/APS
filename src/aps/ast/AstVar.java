package aps.ast;
public class AstVar implements IASTDec{
    Ast name;
    Ast type;

    public AstVar(Ast name, Ast type){
        this.name = name;
        this.type = type;
    }

    public Environment eval(Environment env){
        return null;
    }


    public String toPrologString(){
        return "var("+name.toPrologString()+","+type.toPrologString()+")";
    }

}