public class AstType implements Ast {
    Type type;

    AstType(Type type) {
        this.type = type;
    }

    public String toPrologString() {
        return type.toString();
    }


    public Value eval(Environment env){
        return null;
    }
    
}