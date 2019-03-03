public class AstTrue implements Ast {

    public String toPrologString() {
        return "true";
    }


    public Context eval(Context c){
        return null;
    }
    
}