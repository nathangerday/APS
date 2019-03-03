public class AstFalse implements Ast {

    public String toPrologString() {
        return "false";
    }


    public Context eval(Context c){
        return null;
    }
}