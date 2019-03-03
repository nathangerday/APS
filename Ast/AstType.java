public class AstType implements Ast {
    Type type;

    AstType(Type type) {
        this.type = type;
    }

    public String toPrologString() {
        return type.toString();
    }


    public Context eval(Context c){
        return null;
    }
}