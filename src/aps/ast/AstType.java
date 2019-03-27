package aps.ast;
public class AstType implements Ast {
    Type type;
    AstType inside;

    public AstType(Type type) {
        this.type = type;
        this.inside = null;
    }

    public AstType(Type type, AstType inside){
        this.type = type;
        this.inside = inside;
    }

    public String toPrologString() {
        if(type == Type.VEC){
            return "vec("+inside.toPrologString()+")";
        }else{
            return type.toString();
        }
    }


    public Value eval(Environment env){
        return null;
    }
    
}